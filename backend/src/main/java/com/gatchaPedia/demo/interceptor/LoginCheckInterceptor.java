package com.gatchaPedia.demo.interceptor;

import com.gatchaPedia.demo.member.exception.MemberAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *  스프링 인터셉터를 사용하기 위해서는 HandlerInterceptor를 구현해 줘야 한다
 *  HandlerInterceptor 안에 preHandle, postHandle, afterCompletion 이 들어있음
 */

// 로그인 되지 않은 사용자는 로그인 화면으로 튕겨버리는 가장 1번째 인터셉터
// 컨트롤러를 거칠 필요도 없으니 PreHandle만 구현해서 입장불가 만들어줌

    
// 인터셉터의 역할은 컨트롤러에서 공통적으로 사용해야하는 로직이 너무 많을 때 공통처리를 위해서 사용하는 거라
// 굳이 1~2개 메소드가 이용하는 공통처리라고 보기 어려운 경우에는 그냥 컨트롤러 계층에서 처리해 버려도 상관이 없음
public class LoginCheckInterceptor implements HandlerInterceptor {


    // 로그인을 했는지 안했는지만 체크할거라서 preHandle에서 체크 후
    // 세션을 가지고 있지 않으면 index로 다시 보내버림
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String sessionId = session.getId();  // JSessionId = nabgabgewbiwbwb 이런거 올거임

        // 세션 키자체가 없는 경우와 , 유효하지 않은 키를 넣은 경우
        if (session == null || session.getAttribute(sessionId) == null) {      // session.get 하면 Member가 나와야함 없으면 에러
            throw new MemberAuthException();
        }

        return true;
    }

}
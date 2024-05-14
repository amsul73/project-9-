import React, { useState } from 'react';
import Header from '../component/header';
import '../public/css/main.css'

function MyPage(props) {

    const [activeTab, setActiveTab] = useState('tab1');
    const [checked, setChecked] = useState(false);
    const [password, setPassword] = useState('');

    const deleteMember = async (e) => {
        e.preventDefault();
        if(!checked) {
            alert("안내 사항에 동의해주세요.");
        }
        else if(password === "") {
            alert("비밀번호를 입력해주세요.");
        }
        else {
            //비밀번호 체크 후, 맞으면 데이터 삭제 
            //아니면 비밀번호 오류
        }

    }

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='mypage'>
                <div className='mypage-menu'>
                    <p>메뉴</p>
                    <a href="#" onClick={() => setActiveTab('tab1')}>회원정보 수정</a>
                    <a href="#" onClick={() => setActiveTab('tab2')}>북마크 관리</a>
                    <a href="#" onClick={() => setActiveTab('tab3')}>그 외</a>
                    <a href="#" onClick={() => setActiveTab('tab4')}>회원탈퇴</a>
                </div>
                <div className='mypage-main'>
                    {activeTab === 'tab1' && (
                        <>
                        <p>회원정보 수정</p>
                        <hr />
                        <form action="/" method="post" id="menu-form" >
                            <input type="submit" value="수정" />
                        </form>
                        </>
                    )}
                    {activeTab === 'tab2' && (
                        <>
                        <p>북마크 관리</p>
                        <hr />
                        <form action="/" method="post" id="menu-form" >
                            <input type="submit" value="수정" />
                        </form>
                        </>
                    )}
                    {activeTab === 'tab3' && (
                        <>
                        <p>그 외</p>
                        <hr />
                        <form action="/" method="post" id="menu-form" >
                            <input type="submit" value="수정" />
                        </form>
                        </>
                    )}
                    {activeTab === 'tab4' && (
                        <>
                        <p>회원탈퇴</p>
                        <hr />
                        <div className='caution-box'>
                            회원 탈퇴를 하면 저장된 데이터가 모두 소실될 수 있습니다. <br/>
                            회원 탈퇴를 진행하시겠습니까?
                        </div>
                        <form onSubmit={deleteMember} id="menu-form" >
                            <div className='caution-box'>
                                <input type="checkbox" value={checked} onChange={() => setChecked(!checked)} /> 
                                <span>안내 사항을 모두 확인하였으며, 이에 동의합니다.</span><br /><br />
                                회원 탈퇴 진행을 위해 비밀번호를 입력해주세요.
                            </div>
                            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" /><br />
                            <input type="submit" value="탈퇴하기" />
                        </form>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}

export default MyPage;
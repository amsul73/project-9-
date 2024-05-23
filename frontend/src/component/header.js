import React, { useState } from 'react';
import '../public/css/header.css';
import Poster from '../public/img/poster.jpg';
import cookie from 'react-cookies';
import axios from 'axios';

function logout() {
    axios.post("/api/logout").then(res => {
        if(res.data['success'] === true) {
            alert(res.data['message'])
            cookie.remove('JSESSIONID')
        }
        else {
            console.log("데이터 어디감")
        }
    })
}

function Header(props) {

    let loginStatus = null
    const user = cookie.load('JSESSIONID')
    //const loginData = 1
    //console.log(loginData)

    if(user !== null) {
        try {
            if(1 === 1) { //유효 세션 존재할 때
                loginStatus = [<span>환영합니다! {111}님</span>, <a href="/mypage">마이페이지</a>, <a href="/" onClick={() => logout()}>로그아웃</a>]
            }
        } catch (err) {
            alert("잘못된 접근입니다.")
        }
    }
    else { //유효 세션 없을 때
        loginStatus = [<a href="/login">로그인</a>, <a href="/register">회원가입</a>]
    }

    const [inputText, setInputText] = useState('');

    const handleInputChange = (e) => {
        setInputText(e.target.value);

        //데이터 받아오기
        var movie_list = [
            Poster, Poster, Poster, Poster, Poster, Poster,
            Poster, Poster, Poster, Poster, Poster, Poster,
            Poster, Poster, Poster, Poster, Poster, Poster,
            Poster, Poster, Poster, Poster, Poster, Poster,
            Poster, Poster, Poster, Poster, Poster, Poster
        ]
        
    }

    return (
        <header>
            <a href="/">GachaPEDIA</a>
            <nav className="list-kind">
                <a href="/list?kind=m">영화</a>
                <a href="/list?kind=t">TOP 100</a>
            </nav>
            <nav>
                <input type="search" name="search" value={inputText} onChange={handleInputChange} placeholder="검색"/>
            </nav>
            <nav>
                {loginStatus[0]}
                {loginStatus[1]}
                {loginStatus[2]}
            </nav>
        </header>
    );
}

export default Header;
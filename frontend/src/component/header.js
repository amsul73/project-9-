import React, { useState } from 'react';
import '../public/css/header.css';
import cookie from 'react-cookies';
import axios from 'axios';

function logout() {
    axios.post("/api/logout").then(res => {
        if(res.data['success'] === true) {
            alert(res.data['message'])
            cookie.remove('JSESSIONID')
        }
        else {
            console.log("데이터를 받아오는데 실패했습니다.")
        }
    })
}

function Header(props) {

    let loginStatus = null
    const user = cookie.load('JSESSIONID')

    const [name, setName] = useState(null)

    try {
        axios.get("/api/session").then(res => {
            if(res.data['success'] === true) {
                setName(res.data['memberName'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    } catch(err) {
        console.log("계정 정보 에러")
    }

    if(name !== null) {
        loginStatus = [<span>환영합니다! {name}님</span>, <a href="/mypage">마이페이지</a>, <a href="/" onClick={() => logout()}>로그아웃</a>]
    }
    else { 
        loginStatus = [<a href="/login">로그인</a>, <a href="/register">회원가입</a>]
    }

    const [inputText, setInputText] = useState('');

    const handleInputChange = (e) => {
        setInputText(e.target.value);
    }
    
    const handleSearch = (e) => {
        if (e.keyCode === 13) {
            window.document.location.href = `/list/${inputText}`
        }
      };

    return (
        <header>
            <a href="/">GachaPEDIA</a>
            <nav className="list-kind">
                <a href="/list/all">전체 영화</a>
            </nav>
            <nav>
                <input type="search" name="search" value={inputText} onChange={handleInputChange} onKeyDown={handleSearch} placeholder="검색"/>
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
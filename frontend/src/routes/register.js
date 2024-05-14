import React, { useState } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import axios from 'axios';

const Register = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [pass_confirm, setPassConfirm] = useState('');
    const [realname, setRealname] = useState('');
    const [email, setEmail] = useState('');
    const register = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post("/api/register", {
                username:username,
                password:password,
                pass_confirm:pass_confirm,
                realname:realname,
                email:email,
            })
            //console.log(res.data)
            alert(res.data['message'])
            if(res.data['success'] === true) {
                window.location.href="/"
            }
        } catch(err) {
            console.error(err)
        }
    };

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='login-wrapper'>
                <h3>회원가입</h3>
                <form onSubmit={register} id="login-form" >
                    <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="아이디" />
                    <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" />
                    <input type="password" name="confirm" value={pass_confirm} onChange={(e) => setPassConfirm(e.target.value)}placeholder="비밀번호 확인" />
                    <input type="text" name="name" value={realname} onChange={(e) => setRealname(e.target.value)} placeholder="이름" />
                    <input type="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="이메일" />
                    <input type="submit" value="회원가입" />
                </form>
                <div>
                    <a href="/login">로그인</a>
                </div>
            </div>
        </div>
    );
}

export default Register;
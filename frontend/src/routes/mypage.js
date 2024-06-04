import React, { useState, useEffect } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import axios from 'axios';

function MyPage(props) {

    const [bookmarks, setBookmarks] = useState([]);

    useEffect(() => {
        const checkSession = async () => {
            try {
                axios.get("/api/session").then(res => {
                    if(res.data['errcode'] == 1000) {
                        alert(res.data['message'])
                        window.document.location.href = "/login"
                    }
                })
            } catch(err) {
                window.document.location.href = "/login"
            }
        }

        checkSession();
        bookmarkList();

    }, []);

    const bookmarkList = () => {
        axios.get("/api/bookmarks").then(res => {
            if(res.data['success'] === true) {
                setBookmarks(res.data['bookmarkList'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }
    
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
            axios.post("/api/signout").then(res => {
                if(res.data['success'] === true) {
                    alert(res.data['message'])
                    window.document.href = '/'
                }
                else {
                    console.log("데이터를 받아오는데 실패했습니다.")
                }
            })
        }
    }

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='mypage'>
                <div className='mypage-menu'>
                    <p>메뉴</p>
                    <a href="#" onClick={() => setActiveTab('tab1')}>북마크 관리</a>
                    <a href="#" onClick={() => setActiveTab('tab2')}>회원탈퇴</a>
                </div>
                <div className='mypage-main'>
                    {activeTab === 'tab1' && (
                        <>
                        <p>북마크 관리</p>
                        <hr />
                        {bookmarks.map((bookmark, index) => (
                            <div className='mypage-bookmark' key={index}>
                                <a href={`/information/${bookmark.movieId}`}>{bookmark.title}</a>
                            </div>
                        ))}
                        </>
                    )}
                    {activeTab === 'tab2' && (
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
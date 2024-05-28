import React, { useState, useEffect } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';
import axios from 'axios';

function Main(props) {

    const baseurl = "https://image.tmdb.org/t/p/w500"
    const [gachaimg, setGachaImg] = useState(null);
    const [gachamovieId, setGachaMovieId] = useState(0);
    const [recommend, setRecommend] = useState([]);
    const [notlogin, setNotLogin] = useState(true);

    const Gacha = () => {
        axios.get("/api/reroll").then(res => {
            if(res.data['success'] === true) {
                setGachaMovieId(res.data['movieId'])
                setGachaImg(baseurl + res.data['gatchaPhotoUrl'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }

    const [offset, setOffset] = useState(0);

    const prevButton = () => {
        setOffset(Math.max(offset - 6, 0));
    };
        
    const nextButton = () => {
        setOffset(Math.min(offset + 6, recommend.length - 6));  
    };

    useEffect(() => {
        axios.get("/api/mainpage").then(res => {
            if(res.data['success'] === true) {
                setGachaMovieId(res.data['movieId'])
                setGachaImg(baseurl + res.data['gatchaPhotoUrl'])
                if(res.data['recommendList'] !== null) {
                    setRecommend(res.data['recommendList'])
                    setNotLogin(false)
                }
                else {
                    setNotLogin(true)
                    console.log("아직 북마크가 없습니다.")
                }
            }
            else {
                setNotLogin(true)
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }, [])

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='movie'>
                <a id="g_href" href={`/information/${gachamovieId}`}><img id="g_img" src={gachaimg} /></a>
            </div>
            <div id="reroll">
                <button onClick={Gacha}>Gacha</button>
            </div>
            <div className={`recommend-div ${(notlogin ? "active" : "")}`}>
                <h5>Recommend For You (리뷰기반 추천 목록)</h5>
                <div className='carousel-div'>
                    <button onClick={prevButton} className="carousel-button prev" >
                        ❮
                    </button>
                    <div className="carousel-items">
                        {recommend.slice(offset, offset + 6).map((image, index) => (
                            <a key={index} href={`/information/${image.movieId}`}><img src={baseurl + image.moviePhotoUrl} className="carousel-img" /></a>
                        ))}
                    </div>
                    <button onClick={nextButton} className="carousel-button next">
                        ❯
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Main;
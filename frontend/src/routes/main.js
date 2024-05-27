import React, { useState, useEffect } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';
import axios from 'axios';

function Main(props) {

    const baseurl = "https://image.tmdb.org/t/p/w500"
    const [gachaimg, setGachaImg] = useState(null);
    const [movieId, setMovieId] = useState(0);

    var movie_json = []

    const Gacha = () => {
        axios.get("/api/reroll").then(res => {
            if(res.data['success'] === true) {
                setMovieId(res.data['movieId'])
                setGachaImg(baseurl + res.data['gatchaPhotoUrl'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }
    
    const imgSrcList = [
        Poster,'https://picsum.photos/200/300',Poster,Poster,Poster,'https://picsum.photos/200/300',
        Poster,Poster,'https://picsum.photos/200/300',Poster,Poster,Poster,
        Poster,Poster,Poster,Poster,Poster,Poster,
        Poster,Poster,Poster,Poster,Poster,Poster,
        Poster,Poster,Poster,Poster,Poster,Poster,
        Poster,Poster,Poster,Poster,Poster,Poster,
        Poster,Poster,Poster,Poster,Poster,'https://picsum.photos/200/300',
    ]

    const [offset, setOffset] = useState(0);

    const prevButton = () => {
        setOffset(Math.max(offset - 6, 0));
    };
        
    const nextButton = () => {
        setOffset(Math.min(offset + 6, imgSrcList.length - 6));  
    };

    useEffect(() => {
        axios.get("/api/mainpage").then(res => {
            if(res.data['success'] === true) {
                setMovieId(res.data['movieId'])
                setGachaImg(baseurl + res.data['gatchaPhotoUrl'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }, [])

    

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='movie'>
                <a id="g_href" href={`/information/${movieId}`}><img id="g_img" src={gachaimg} /></a>
            </div>
            <div id="reroll">
                <button onClick={Gacha}>Gacha</button>
            </div>
            <h5>Recommend For You (리뷰기반 추천 목록)</h5>
            <div className='carousel-div'>
                <button onClick={prevButton} className="carousel-button prev" >
                    ❮
                </button>
                <div className="carousel-items">
                    {imgSrcList.slice(offset, offset + 6).map((image, index) => (
                        <a key={index} href='/information'><img src={image} className="carousel-img" /></a>
                    ))}
                </div>
                <button onClick={nextButton} className="carousel-button next">
                    ❯
                </button>
            </div>
        </div>
    );
}

export default Main;
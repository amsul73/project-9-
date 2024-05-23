import React, { useState } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';
import axios from 'axios';

function Main(props) {

    var movie_json = []

    const Gacha = () => {
        axios.get("/api/reroll").then(res => {
            console.log(res.data)
            if(res.data['success'] === true) {
                movie_json = res.data['movieList']
                console.log(movie_json)
            }
            else {
                console.log("데이터 어디감")
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

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='movie'>
                <div><a href='/information'><img src={Poster} /></a></div>
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
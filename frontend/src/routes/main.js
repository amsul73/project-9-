import React, { useState } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';

function Main(props) {

    const Gacha = () => {
        // server get
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
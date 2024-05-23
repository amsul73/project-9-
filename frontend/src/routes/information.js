import React from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';

function Information(props) {
    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='information-movie'>
                <div className='movie-poster'>
                    <img src={Poster}/>
                </div>
                <div className='movie-header'>
                    <div className='movie-title'>
                        <div>영화 제목</div>
                        <button>북마크</button>
                    </div>
                    <div className='movie-description'>
                        <div className='movie-summary'>
                            <p>줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리</p>
                            <p>줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리</p>
                            <p>줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리</p>
                            <p>줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리줄거리</p>                            
                        </div>
                        <div className='rating'>
                            <p>평점</p>
                            <div className="star-rating">
                                <input type="radio" className="star" value="1" />
                                <input type="radio" className="star" value="2" />
                                <input type="radio" className="star" value="3" />
                                <input type="radio" className="star" value="4" />
                                <input type="radio" className="star" value="5" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='movie-comment'>
                asas
            </div>
            <div className='movie-comment'>
                asas
            </div>
        </div>
    );
}

export default Information;
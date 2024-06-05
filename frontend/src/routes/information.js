import React, { useState, useEffect } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Star from '../public/img/star.png';
import Half_star from '../public/img/half_star.png';
import Empty_star from '../public/img/empty_star.png';

function Information(props) {

    const { movieId } = useParams();

    const baseurl = "https://image.tmdb.org/t/p/w500"
    
    const [movieimg, setMovieImg] = useState(null);
    const [title, setTitle] = useState(null);
    const [overview, setOverview] = useState(null);
    const [rating, setRating] = useState(null);
    const [genres, setGenres] = useState(null);
    const [tags, setTags] = useState(null);

    const [bookmark, setBookmark] = useState(false)

    useEffect(() => {
        axios.get(`/api/movie/${movieId}`).then(res => {
            if(res.data['success'] === true) {
                setBookmark(res.data['bookmarkChecked'])
                setMovieImg(baseurl + res.data['moviePhotoUrl'])
                setTitle(res.data['title'])
                setOverview(res.data['overView'])
                setRating(res.data['rating'])
                setGenres(res.data['genres'])
                setTags(res.data['tags'])
                //console.log(res.data)
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        })
    }, [])

    const renderStars = () => {
        const stars = [];
        for (let i = 1; i <= 5; i++) {
            if (i <= Math.floor(rating)) {
                stars.push(<img className="star" key={i} src={Star}/>);
            } 
            else if (i === Math.ceil(rating) && rating % 1 !== 0) {
                stars.push(<img className="star" key={i} src={Half_star} />);
            } 
            else {
                stars.push(<img className="star" key={i} src={Empty_star}/>);
            }
        }
        return stars;
      };

    const BookMark = () => {
        axios.post(`/api/bookmark/${movieId}`).then(res => {
            if(res.data['errcode'] == 1000) {
                alert(res.data['message'])
                window.document.location = "/login"
            }
            if(res.data['success'] === true) {
                setBookmark(true)
            }
            else {
                setBookmark(false)
            }
        })
    }

    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='information-movie'>
                <div className='movie-poster'>
                    <img src={movieimg}/>
                    <div className='movie-genre'>
                        <div>장르</div>
                        {(genres !== null) ? 
                        genres.map((elem, idx) => {
                            return (<div className='genre-item'>{elem['name']}</div>)
                        })
                        : 0
                        }
                    </div>
                </div>
                <div className='movie-header'>
                    <div className='movie-head'>
                        <div className='movie-title'>{title}</div>
                        <button className={`${(bookmark ? "active" : "")}`} onClick={() => BookMark()}>북마크</button>
                    </div>
                    <div className='movie-description'>
                        <div className='movie-summary'>
                            <p>개요</p>
                            <hr />
                            <p>{overview}</p>
                        </div>
                        <div className='rating'>
                            <p>평점</p>
                            <div className="star-rating">
                                <p>{rating}</p>
                                <p>{renderStars()}</p>
                            </div>
                        </div>
                        <div className='movie-tags'>
                            <div>태그</div>
                            {(genres !== null) ? 
                            tags.map((elem, idx) => {
                                return (<span className='tag-item'>{elem['name']}</span>)
                            })
                            : 0
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Information;
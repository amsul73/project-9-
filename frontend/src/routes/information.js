import React, { useState, useEffect } from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import { useParams } from 'react-router-dom';
import axios from 'axios';

function Information(props) {

    const { movieId } = useParams();

    const baseurl = "https://image.tmdb.org/t/p/w500"
    
    const [movieimg, setMovieImg] = useState(null);
    const [title, setTitle] = useState(null);
    const [overview, setOverview] = useState(null);
    const [rating, setRating] = useState(null);
    const [genres, setGenres] = useState({name:null});
    const [tags, setTags] = useState({name:null});

    const [bookmark, setBookmark] = useState(false)

    useEffect(() => {
        axios.get(`/api/movie/${movieId}`).then(res => {
            if(res.data['success'] === true) {
                setBookmark(res.data['bookmarkChecked'])
                setMovieImg(baseurl + res.data['moviePhotoUrl'])
                setTitle(res.data['title'])
                setOverview(res.data['overView'])
                setRating(res.data['rating'])
                //setGenres([...genres, {name:res.data['genres']}]) 
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
                stars.push(<span key={i}>&#9733;</span>);
            } 
            else if (i === Math.ceil(rating) && rating % 1 !== 0) {
                stars.push(<span key={i}>&#9731;</span>);
            } 
            else {
                stars.push(<span key={i}>&#9734;</span>);
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
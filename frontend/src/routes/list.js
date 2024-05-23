import React from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';
import axios from 'axios';

function List(props) {

    //get movie at backend (= move_list)

    var movie_json = []

    try {
        axios.get("/api/movies/1").then(res => {
            console.log(res.data)
            if(res.data['success'] === true) {
                movie_json = res.data['movieList']
                console.log(movie_json)
            }
            else {
                console.log("데이터 어디감")
            }
        })
    } catch(err) {
        console.error(err)
    }

    var movie_list = [
        Poster, Poster, Poster, Poster, Poster, Poster,
        Poster, Poster, Poster, Poster, Poster, Poster,
        Poster, Poster, Poster, Poster, Poster, Poster,
        Poster, Poster, Poster, Poster, Poster, Poster,
        Poster, Poster, Poster, Poster, Poster, Poster
    ]
    
    return (
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='movie-list'>
                {movie_list.map((elem, idx) => {
                    return (<div key={idx} className='grid-item'><img className='grid-img' src={elem} /></div>)
                })}
            </div>
        </div>
    );
}

export default List;
import React from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import Poster from '../public/img/poster.jpg';

function List(props) {

    //get movie at backend (= move_list)

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
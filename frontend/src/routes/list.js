import React, {useState, useEffect} from 'react';
import Header from '../component/header';
import '../public/css/main.css'
import axios from 'axios';
import Pagination from "react-js-pagination";
import { useParams } from 'react-router-dom';

function List(props) {

    const { search } = useParams();

    const baseurl = "https://image.tmdb.org/t/p/w500"

    const [movieId, setMovieId] = useState([]);

    const [movieList, setMovieList] = useState([])
    const [titleList, setTitleList] = useState([])
    const [totalPages, setTotalPages] = useState(0);
   
    const [page, setPage] = useState(1);

    const handlePageChange = (page) => {
        setPage(page);
    };
    

    useEffect(() => {
        if(search === 'all') {
            fetchMovies(page)
        }
        else{ 
            searchMovies(page)
        }
    }, [page])

    const fetchMovies = (page) => {
        axios.get(`/api/movies/${page - 1}`).then(res => {
            if(res.data['success'] === true) {
                var list = res.data['movieList']
                const urls = list.map(d => d['moviePhotoUrl'])
                const titles = list.map(d => d['title'])
                const id = list.map(d => d['movieId'])
                setMovieId(id)
                setMovieList(urls)
                setTitleList(titles)
                setTotalPages(res.data['totalPage'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        }).catch(err => {
            console.error(err)
        })
    }

    const searchMovies = (page) => {
        axios.post(`/api/movies/search/${page - 1}`, {keyword:search}).then(res => {
            if(res.data['success'] === true) {
                console.log(res.data)
                var list = res.data['movieList']
                const urls = list.map(d => d['moviePhotoUrl'])
                const titles = list.map(d => d['title'])
                const id = list.map(d => d['movieId'])
                setMovieId(id)
                setMovieList(urls)
                setTitleList(titles)
                setTotalPages(res.data['totalPage'])
            }
            else {
                console.log("데이터를 받아오는데 실패했습니다.")
            }
        }).catch(err => {
            console.error(err)
        })
    }

    return (        
        <div className='App'>
            <Header />
            <div className="logo">GachaPEDIA</div>
            <div className='movie-list'>
                {movieList.map((elem, idx) => {
                    return (<div key={idx} className='grid-item'><a id="g_href" href={`/information/${movieId[idx]}`}><img className='grid-img' src={baseurl + elem} /><p className='grid-title'>{titleList[idx]}</p></a></div>)
                })}
            </div>
            <Pagination
                activePage={page}
                itemsCountPerPage={24}
                totalItemsCount={totalPages*24}
                pageRangeDisplayed={9}
                prevPageText={"‹"}
                nextPageText={"›"}
                onChange={handlePageChange}
            />
        </div>
    );
}

export default List;
# TMDB Scrapping

### Dependency

```sh
# Scrapping & DB config
pip install pandas sqlalchemy mysql-connector-python requests shutil

# Cosine similarity api
pip install scikit-learn flask

# For additional stop_words option - NO NEEDED!!
# pip install nltk
```

---

### DB config

- Windows

```
script.bat
```

- Linux

```
./script.sh
```

---

### API server(Cosine Similarity)

```sh
python3 app.py or python app.py
```

### OPEN API example

```json
{
  "adult": false,
  "backdrop_path": "/lxD5ak7BOoinRNehOCA85CQ8ubr.jpg",
  "belongs_to_collection": {
    "id": 10194,
    "name": "Toy Story Collection",
    "poster_path": "/7G9915LfUQ2lVfwMEEhDsn3kT4B.jpg",
    "backdrop_path": "/9FBwqcd9IRruEDUrTdcaafOMKUq.jpg"
  },
  "budget": 30000000,
  "genres": [
    { "id": 16, "name": "Animation" },
    { "id": 12, "name": "Adventure" },
    { "id": 10751, "name": "Family" },
    { "id": 35, "name": "Comedy" }
  ],
  "homepage": "http://toystory.disney.com/toy-story",
  "id": 862,
  "imdb_id": "tt0114709",
  "origin_country": ["US"],
  "original_language": "en",
  "original_title": "Toy Story",
  "overview": "Led by Woody, Andy's toys live happily in his room until Andy's birthday brings Buzz Lightyear onto the scene. Afraid of losing his place in Andy's heart, Woody plots against Buzz. But when circumstances separate Buzz and Woody from their owner, the duo eventually learns to put aside their differences.",
  "popularity": 236.598,
  "poster_path": "/uXDfjJbdP4ijW5hWSBrPrlKpxab.jpg",
  "production_companies": [
    {
      "id": 3,
      "logo_path": "/1TjvGVDMYsj6JBxOAkUHpPEwLf7.png",
      "name": "Pixar",
      "origin_country": "US"
    }
  ],
  "production_countries": [
    { "iso_3166_1": "US", "name": "United States of America" }
  ],
  "release_date": "1995-10-30",
  "revenue": 394436586,
  "runtime": 81,
  "spoken_languages": [
    { "english_name": "English", "iso_639_1": "en", "name": "English" }
  ],
  "status": "Released",
  "tagline": "Hang on for the comedy that goes to infinity and beyond!",
  "title": "Toy Story",
  "video": false,
  "vote_average": 7.971,
  "vote_count": 17869
}
```

import pandas as pd
from sqlalchemy import create_engine

username = "root"
password = ""
host = "localhost"
database = "movie"

csv_file_path = "data/scrapping.csv"

engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")

df = pd.read_csv(csv_file_path)

df.rename(columns={
    'Movie ID': 'movie_id',
    'Original Title': 'title',
    'Overview': 'over_view',
    'Poster Path': 'movie_photourl'
}, inplace=True)

df.to_sql(
    name='movie',
    con=engine,
    index=False,
    if_exists='append'
)

print(f"Data from '{csv_file_path}' has been appended to the 'movie' table in the database.")

filename = "movies"
csv_file_path = f"data/{filename}.csv"

df = pd.read_csv(csv_file_path)

df['genres'] = df['genres'].str.split('|')

genre_df = pd.DataFrame(columns=['movie_id', 'genre_id', 'name'])

with engine.connect() as connection:
    valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
    valid_movie_ids = set(valid_movie_ids['movie_id'])

genre_id = 1

for index, row in df.iterrows():
    movie_id = row['movie_id']
    if movie_id in valid_movie_ids:
        genres = row['genres']
        for genre in genres:
            genre_df.loc[len(genre_df)] = {'movie_id': movie_id, 'genre_id': genre_id, 'name': genre}
            genre_id += 1

genre_df.to_sql(
    name="genre",
    con=engine,
    index=False,
    if_exists="append"
)

print(f"Data from '{csv_file_path}' has been inserted into the 'genre' table in the database.")

csv_file_path = "data/tags.csv"

df = pd.read_csv(csv_file_path)

with engine.connect() as connection:
    valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
    valid_movie_ids = set(valid_movie_ids['movie_id'])

valid_tags_df = df[df['movie_id'].isin(valid_movie_ids)]

tag_df = pd.DataFrame({
    'tag_id': range(1, len(valid_tags_df) + 1),
    'name': valid_tags_df['tag'],
    'movie_id': valid_tags_df['movie_id']
})

tag_df.to_sql(
    name="tag",
    con=engine,
    index=False,
    if_exists="append"
)

print(f"New tags from '{csv_file_path}' have been inserted into the 'tag' table in the database.")

data = {
    'member_id': range(1, 701),
    'email': [f"test{i}@test.com" for i in range(1, 701)],
    'name': [f"이름{i}" for i in range(1, 701)],
    'password': ['Test123456!'] * 700,
    'username': [f"testdata{i}" for i in range(1, 701)]
}
df = pd.DataFrame(data)

df.to_sql(
    name='member',
    con=engine,
    index=False,
    if_exists='append'
)

print("Data has been appended to the 'DB_member' table in the database.")

ratings_df = pd.read_csv("data/ratings.csv")
ratings_df = ratings_df[['userId', 'movieId', 'rating']]
ratings_df.columns = ['member_id', 'movie_id', 'rating']
ratings_df['rating_id'] = range(1, len(ratings_df) + 1)

existing_movie_ids = pd.read_sql("SELECT DISTINCT movie_id FROM movie", engine)['movie_id']

ratings_df = ratings_df[ratings_df['movie_id'].isin(existing_movie_ids)]

ratings_df.to_sql(
    name='rating',
    con=engine,
    index=False,
    if_exists='append',
    chunksize=1000,
    method='multi',
)

print("Data from 'ratings.csv' has been appended to the 'rating' table in the database.")


# # movie 넣기
#
# # import pandas as pd
# # from sqlalchemy import create_engine
# #
# # # DB 정보 개인 환경에 맞춰서 넣어주세요
# # username = "root"
# # password = "worldcup7"
# # host = "localhost"
# # database = "movie"
# #
# # # 데이터베이스 엔진 생성
# # engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
# #
# # # 파일 이름과 경로 설정
# # csv_file_path = "data/scrapping.csv"
# #
# # # CSV 파일을 데이터프레임으로 읽어오기 (전체 열 읽어오기)
# # df = pd.read_csv(csv_file_path)
# #
# # # 열 이름을 데이터베이스 테이블에 맞게 변경
# # df.rename(columns={
# #     'Movie ID': 'movie_id',
# #     'Original Title': 'title',
# #     'Overview': 'over_view',
# #     'Poster Path': 'movie_photourl'
# # }, inplace=True)
# #
# # # 데이터프레임을 기존 테이블에 삽입
# # df.to_sql(
# #     name='movie',  # 테이블 이름을 "movie"로 설정
# #     con=engine,
# #     index=False,
# #     if_exists='append'  # 존재하는 테이블에 추가하기
# # )
# #
# # print(f"Data from '{csv_file_path}' has been appended to the 'movie' table in the database.")
#
#
#
#
#
# #
# # import pandas as pd
# # from sqlalchemy import create_engine
# #
# # # DB 정보 개인 환경에 맞춰서 수정해주세요
# # username = "root"
# # password = "worldcup7"
# # host = "localhost"
# # database = "movie"
# #
# # # 데이터베이스 엔진 생성
# # engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
# #
# # # 파일 이름과 경로 설정
# # filename = "movies"
# # csv_file_path = f"data/{filename}.csv"
# #
# # # 데이터셋 로드
# # df = pd.read_csv(csv_file_path)
# #
# # # 'genres' 열을 파이프(|)로 분리하여 리스트로 변환
# # df['genres'] = df['genres'].str.split('|')
# #
# # # 장르 정보를 저장할 데이터프레임 생성
# # genre_df = pd.DataFrame(columns=['movie_id', 'genre_id', 'name'])
# #
# # # movie 테이블에서 유효한 movie_id 가져오기
# # with engine.connect() as connection:
# #     valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
# #     valid_movie_ids = set(valid_movie_ids['movie_id'])  # set으로 변환하여 검색 속도 향상
# #
# # # 장르 아이디 초기값 설정
# # genre_id = 1
# #
# # # 장르 정보 추출하여 데이터프레임에 추가
# # for index, row in df.iterrows():
# #     movie_id = row['movie_id']
# #     if movie_id in valid_movie_ids:  # movie_id가 유효한 경우에만 처리
# #         genres = row['genres']
# #         for genre in genres:
# #             genre_df.loc[len(genre_df)] = {'movie_id': movie_id, 'genre_id': genre_id, 'name': genre}
# #             genre_id += 1  # 장르 아이디 증가
# #
# # # genre 테이블에 데이터 삽입
# # genre_df.to_sql(
# #     name="genre",  # 테이블 이름 설정
# #     con=engine,
# #     index=False,
# #     if_exists="append"  # 존재하는 테이블에 추가하기
# # )
# #
# # print(f"Data from '{csv_file_path}' has been inserted into the 'genre' table in the database.")
#
# #
# # import pandas as pd
# # from sqlalchemy import create_engine
# #
# # # DB 정보를 개인 환경에 맞춰서 수정해주세요
# # username = "root"
# # password = "worldcup7"
# # host = "localhost"
# # database = "movie"
# #
# # # 데이터베이스 엔진 생성
# # engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
# #
# # # 파일 이름을 확인하고 수정하세요
# # csv_file_path = "data/tags.csv"
# #
# # # CSV 파일을 데이터프레임으로 로드
# # df = pd.read_csv(csv_file_path)
# #
# # # movie 테이블에서 유효한 movie_id 가져오기
# # with engine.connect() as connection:
# #     valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
# #     valid_movie_ids = set(valid_movie_ids['movie_id'])  # set으로 변환하여 검색 속도 향상
# #
# # # 유효한 movie_id를 가진 태그 데이터만 선택하여 새로운 데이터프레임 생성
# # valid_tags_df = df[df['movie_id'].isin(valid_movie_ids)]
# #
# # # 태그 테이블에 새로운 태그 데이터 삽입
# # tag_df = pd.DataFrame({
# #     'tag_id': range(1, len(valid_tags_df) + 1),  # 태그 ID는 1부터 1씩 증가
# #     'name': valid_tags_df['tag'],  # 태그명은 유효한 태그 데이터에서 가져옴
# #     'movie_id': valid_tags_df['movie_id']  # 영화 ID는 유효한 태그 데이터에서 가져옴
# # })
# #
# # tag_df.to_sql(
# #     name="tag",  # 테이블 이름 설정
# #     con=engine,
# #     index=False,
# #     if_exists="append"  # 존재하는 테이블에 추가하기
# # )
# #
# # print(f"New tags from '{csv_file_path}' have been inserted into the 'tag' table in the database.")
#
#
#
#
# # 1~700 까지 멤버 넣기
# # import pandas as pd
# # from sqlalchemy import create_engine
# #
# # # DB 정보 개인 환경에 맞춰서 넣어주세요
# # username = "root"
# # password = "worldcup7"
# # host = "localhost"
# # database = "movie"
# #
# # # 데이터베이스 엔진 생성
# # engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
# #
# # # 데이터프레임 생성
# # data = {
# #     'member_id': range(1, 701),
# #     'email': [f"test{i}@test.com" for i in range(1, 701)],
# #     'name': [f"이름{i}" for i in range(1, 701)],
# #     'password': ['Test123456!'] * 700,
# #     'username': [f"testdata{i}" for i in range(1, 701)]
# # }
# # df = pd.DataFrame(data)
# #
# # # 데이터프레임을 기존 테이블에 삽입
# # df.to_sql(
# #     name='member',  # 테이블 이름을 "DB_member"로 설정
# #     con=engine,
# #     index=False,
# #     if_exists='append'  # 존재하는 테이블에 추가하기
# # )
# #
# # print("Data has been appended to the 'DB_member' table in the database.")
#
#
#
# import pandas as pd
# from sqlalchemy import create_engine
#
# # DB 정보 개인 환경에 맞춰서 넣어주세요
# username = "root"
# password = "worldcup7"
# host = "localhost"
# database = "movie"
#
# # 데이터베이스 엔진 생성
# engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
#
# # ratings.csv 파일 읽기
# ratings_df = pd.read_csv("data/ratings.csv")
#
# # 필요한 열만 선택
# ratings_df = ratings_df[['userId', 'movieId', 'rating']]
#
# # 열 이름 변경
# ratings_df.columns = ['member_id', 'movie_id', 'rating']
#
# # rating_id 추가
# ratings_df['rating_id'] = range(1, len(ratings_df) + 1)
#
# # 데이터베이스에 존재하는 영화 ID 목록 가져오기
# existing_movie_ids = pd.read_sql("SELECT DISTINCT movie_id FROM movie", engine)['movie_id']
#
# # 데이터베이스에 영화 ID가 존재하지 않는 레코드 필터링
# ratings_df = ratings_df[ratings_df['movie_id'].isin(existing_movie_ids)]
#
# # 데이터베이스에 삽입
# ratings_df.to_sql(
#     name='rating',  # 테이블 이름을 "rating"로 설정
#     con=engine,
#     index=False,
#     if_exists='append',  # 존재하는 테이블에 추가하기
#     chunksize=1000,  # 한 번에 삽입하는 데이터 양 설정
#     method='multi',  # 데이터베이스에 데이터를 삽입하는 방법 설정
# )
#
# print("Data from 'ratings.csv' has been appended to the 'rating' table in the database.")



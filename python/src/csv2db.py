# movie 넣기

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
# # 파일 이름과 경로 설정
# csv_file_path = "data/scrapping.csv"
#
# # CSV 파일을 데이터프레임으로 읽어오기 (전체 열 읽어오기)
# df = pd.read_csv(csv_file_path)
#
# # 열 이름을 데이터베이스 테이블에 맞게 변경
# df.rename(columns={
#     'Movie ID': 'movie_id',
#     'Original Title': 'title',
#     'Overview': 'over_view',
#     'Poster Path': 'movie_photourl'
# }, inplace=True)
#
# # 데이터프레임을 기존 테이블에 삽입
# df.to_sql(
#     name='movie',  # 테이블 이름을 "movie"로 설정
#     con=engine,
#     index=False,
#     if_exists='append'  # 존재하는 테이블에 추가하기
# )
#
# print(f"Data from '{csv_file_path}' has been appended to the 'movie' table in the database.")





#
# import pandas as pd
# from sqlalchemy import create_engine
#
# # DB 정보 개인 환경에 맞춰서 수정해주세요
# username = "root"
# password = "worldcup7"
# host = "localhost"
# database = "movie"
#
# # 데이터베이스 엔진 생성
# engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
#
# # 파일 이름과 경로 설정
# filename = "movies"
# csv_file_path = f"data/{filename}.csv"
#
# # 데이터셋 로드
# df = pd.read_csv(csv_file_path)
#
# # 'genres' 열을 파이프(|)로 분리하여 리스트로 변환
# df['genres'] = df['genres'].str.split('|')
#
# # 장르 정보를 저장할 데이터프레임 생성
# genre_df = pd.DataFrame(columns=['movie_id', 'genre_id', 'name'])
#
# # movie 테이블에서 유효한 movie_id 가져오기
# with engine.connect() as connection:
#     valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
#     valid_movie_ids = set(valid_movie_ids['movie_id'])  # set으로 변환하여 검색 속도 향상
#
# # 장르 아이디 초기값 설정
# genre_id = 1
#
# # 장르 정보 추출하여 데이터프레임에 추가
# for index, row in df.iterrows():
#     movie_id = row['movie_id']
#     if movie_id in valid_movie_ids:  # movie_id가 유효한 경우에만 처리
#         genres = row['genres']
#         for genre in genres:
#             genre_df.loc[len(genre_df)] = {'movie_id': movie_id, 'genre_id': genre_id, 'name': genre}
#             genre_id += 1  # 장르 아이디 증가
#
# # genre 테이블에 데이터 삽입
# genre_df.to_sql(
#     name="genre",  # 테이블 이름 설정
#     con=engine,
#     index=False,
#     if_exists="append"  # 존재하는 테이블에 추가하기
# )
#
# print(f"Data from '{csv_file_path}' has been inserted into the 'genre' table in the database.")

#
# import pandas as pd
# from sqlalchemy import create_engine
#
# # DB 정보를 개인 환경에 맞춰서 수정해주세요
# username = "root"
# password = "worldcup7"
# host = "localhost"
# database = "movie"
#
# # 데이터베이스 엔진 생성
# engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
#
# # 파일 이름을 확인하고 수정하세요
# csv_file_path = "data/tags.csv"
#
# # CSV 파일을 데이터프레임으로 로드
# df = pd.read_csv(csv_file_path)
#
# # movie 테이블에서 유효한 movie_id 가져오기
# with engine.connect() as connection:
#     valid_movie_ids = pd.read_sql("SELECT movie_id FROM movie", connection)
#     valid_movie_ids = set(valid_movie_ids['movie_id'])  # set으로 변환하여 검색 속도 향상
#
# # 유효한 movie_id를 가진 태그 데이터만 선택하여 새로운 데이터프레임 생성
# valid_tags_df = df[df['movie_id'].isin(valid_movie_ids)]
#
# # 태그 테이블에 새로운 태그 데이터 삽입
# tag_df = pd.DataFrame({
#     'tag_id': range(1, len(valid_tags_df) + 1),  # 태그 ID는 1부터 1씩 증가
#     'name': valid_tags_df['tag'],  # 태그명은 유효한 태그 데이터에서 가져옴
#     'movie_id': valid_tags_df['movie_id']  # 영화 ID는 유효한 태그 데이터에서 가져옴
# })
#
# tag_df.to_sql(
#     name="tag",  # 테이블 이름 설정
#     con=engine,
#     index=False,
#     if_exists="append"  # 존재하는 테이블에 추가하기
# )
#
# print(f"New tags from '{csv_file_path}' have been inserted into the 'tag' table in the database.")







import pandas as pd
from sqlalchemy import create_engine


# # 오류남 rating
# import pandas as pd
# from sqlalchemy import create_engine
#
# # DB 정보 개인 환경에 맞춰서 수정해주세요
# username = "root"
# password = "worldcup7"
# host = "localhost"
# database = "movie"
#
# # 데이터베이스 엔진 생성
# engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
#
# # 파일 이름 잘 확인해주세요.. 이름 다른거 있으면 바꿔야 해요
# filename = "ratings"
# csv_file_path = f"data/{filename}.csv"
#
# # 데이터셋 로드
# df = pd.read_csv(csv_file_path)
#
# # 기존 member 테이블에 있는 member_id의 최대값 찾기
# max_member_id_query = pd.read_sql("SELECT MAX(member_id) FROM member", con=engine)
# if max_member_id_query.iloc[0, 0] is not None:
#     max_member_id = max_member_id_query.iloc[0, 0]
# else:
#     max_member_id = 0
#
# # 멤버 아이디 부여
# df['member_id'] = range(max_member_id + 1, max_member_id + 1 + len(df))
#
# # rating 테이블에 데이터 삽입
# df[['member_id', 'movie_id', 'rating']].to_sql(
#     name="rating",  # 테이블 이름 설정
#     con=engine,
#     index=False,
#     if_exists="append"  # 존재하는 테이블에 추가하기
# )
#
# print(f"Data from '{csv_file_path}' has been inserted into the 'rating' table in the database.")

# import pandas as pd
# from sqlalchemy import create_engine
#
# # DB 정보를 개인 환경에 맞게 수정하세요
# username = "root"
# password = "worldcup7"
# host = "localhost"
# database = "movie"
#
# # 데이터베이스 엔진 생성
# engine = create_engine(f"mysql+mysqlconnector://{username}:{password}@{host}/{database}")
#
# # 파일 이름 확인 및 수정
# filename = "ratings"
# csv_file_path = f"data/{filename}.csv"
#
# # CSV 파일 불러오기
# df = pd.read_csv(csv_file_path)
#
# # rating_id 열을 추가하여 1부터 1씩 증가하는 값으로 설정
# df["rating_id"] = range(1, len(df) + 1)
#
# # timestamp 열 제외
# df = df.drop(columns=["timestamp"])
#
# # userId를 member_id로 변경
# df = df.rename(columns={"userId": "member_id"})
#
# # rating 테이블에 데이터 삽입
# df.to_sql(
#     name="rating",  # 테이블 이름 설정
#     con=engine,
#     index=False,
#     if_exists="append"  # 존재하는 테이블에 추가하기
# )
#
# print(f"Data from '{csv_file_path}' has been inserted into the 'rating' table in the database.")

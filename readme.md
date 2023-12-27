## 개요
<hr>

    - 미디엄은 블로그 플랫폼 이다.

    - 본인의 글을 유료화 할 수 있다.

    - 미디엄에 가입 이후에 유료 멤버십에 가입하면 유료글을 볼 수 있다.

    - 멤버십은 유지비용은 달에 2천원이다.

    - 미디엄은 한달에 한번 유료글 작성자에게 조회수를 기준으로 멤버십 수익의 일정부분을 캐시로 정산해준다.

    - 해당 캐시는 사이트내에서 돈처럼 사용가능하고 원할 때 환전할 수 있다.

##  회원기능
<hr>

### 엔드 포인트

가입
- GET /member/join : 가입 폼

- POST /member/join : 가입 폼 처리

로그인
- GET /member/login : 로그인 폼

- POST /member/login : 로그인 폼 처리

로그아웃
- POST /member/logout : 로그아웃

폼
회원가입 폼
- username
- password

- passwordConfirm

로그인 폼
- username

- password

## 게시글 CRUD
<hr>

### 엔드 포인트
홈
- GET / : 홈

    최신글 30개 노출

글 목록 조회
- GET /post/list : 전체 글 리스트

공개된 글만 노출

내 글 목록 조회
- GET /post/myList : 내 글 리스트

글 상세내용 조회
- GET /post/1 : 1번 글 상세보기

글 작성
- GET /post/write : 글 작성 폼

- POST /post/write : 글 작성 처리

글 수정
- GET /post/1/modify : 1번 글 수정 폼

- PUT /post/1/modify : 1번 글 수정 폼 처리

글 삭제
- DELETE /post/1/delete : 1번 글 삭제

특정 회원의 글 모아보기
- GET /b/user1 : 회원 user1 의 전체 글 리스트

- GET /b/user1/3 : 회원 user1 의 글 중에서 3번글 상세보기

폼
글 쓰기 폼
- title

- body

- isPublished

  체크박스

  value="true"

글 수정 폼
- title

- body

- isPublished

  체크박스

  value="true"


##  Member 클래스에 private boolean isPaid 필드를 추가
<hr>

  - 해당 필드가 true 인 사람이 로그인할 때, ROLE_PAID 권한도 가지도록(스프링 시큐리티)

- 해당 필드가 true 이면 유료 멤버십 회원 입니다.

## Post 클래스에 private boolean isPaid 필드를 추가
<hr>

- 해당 필드가 true 인 글은 유료회원이 아닌사람에게는 상세보기(GET /post/1)에서 본문(content)이 나올 자리에 이 글은 유료멤버십전용 입니다. 라는 문구가 나온다.

### 엔드 포인트

- GET /post/list

        멤버십 회원이 아니라도 글 리스트에서는 멤버십 전용글을 볼 수 있습니다.

- GET /post/1

        유료 멤버십 회원이고 1번 글이 멤버십전용글 이라면 볼 수 있다.

        그 외에는 이 글은 유료멤버십전용 입니다. 노출

## NotProd 에서 유료멤버십 회원(샘플 데이터)과 유료글(샘플 데이터)을 각각 100개 이상 생성
<hr>
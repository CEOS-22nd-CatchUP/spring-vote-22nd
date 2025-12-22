# spring-vote-22nd
ceos back-end 22nd voting service project

## Swagger
https://ceos-22nd-catchup.github.io/swagger-ui/

## ERD
<img width="1453" height="906" alt="image" src="https://github.com/user-attachments/assets/5241ad10-47b1-4118-b647-10e80ed5dc1a" />

## 기능
### 로그인
- JWT
- AccessToken 기반
- 일회성 서비스임을 고려하여 RefreshToken 및 로그아웃 미구현

### 회원가입
- 아이디, 비밀번호, 이메일, 파트, 이름, 팀
- 아이디와 이메일 중복 여부는 회원가입 요청 시점에서 일괄 검사
- 파트와 팀은 각각 PartType과 TeamType으로 정의해서 일관성 확보

### 투표
- 레포지토리 단에서 득표 순으로 내림차순 정렬하여 반환.
- 파트장 투표와 팀 투표는 반드시 로그인한 사용자만 가능.
- 한 아이디 당 한 번만 투표 가능 (1인 1표).
- 투표 페이지에 접근할 수는 있지만, 투표에 참여할 수는 없음.
- 파트장 투표 시 본인의 파트에 해당하는 파트장 투표만 가능함.
	- 프론트엔드에서 로그인한 사용자의 파트 소속을 확인해서 애초에 백엔드 투표 페이지에 접근할 수 없도록 조치함.
	- 다만 백엔드에서도 이를 검증하는 로직을 추가하였음.
- 데모데이 투표 시 본인이 속한 팀에는 투표를 할 수 없음.

## 서버 배포 전략
- OCI 인스턴스
- 수동 배포
	- Spring과 MySQL 서버만 필요한 간단한 구조였음
	- 이미 OCI 환경에 기존에 구축한 환경으로 서버를 구동하기 충분했기 때문에 Github Action을 통한 Docker 컨테이너 기반 배포 자동화를 도입하지는 않았음

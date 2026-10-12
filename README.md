# Session 12 - Ex4: Deploy Container len VPS qua SSH

## Muc tieu
Push len `main` -> build + push Image len GHCR -> SSH vao VPS pull Image moi va run lai container.

## Secrets can khai bao (repo -> Settings -> Secrets and variables -> Actions)
- `SSH_HOST`: IP/domain VPS.
- `SSH_USER`: user SSH (vd `root`, `ubuntu`).
- `SSH_KEY`: private key (noi dung file `.pem`).
- `GITHUB_TOKEN`: co san, dung de login GHCR (khong can tao).

Tren VPS can cai san: Docker, da `docker login ghcr.io` it nhat 1 lan (neu Image private thi them PAT co quyen `read:packages`).

## Workflow `.github/workflows/deploy-vps.yml`
- Job `build-image`: test -> login GHCR -> build-push `ghcr.io/<owner>/<repo>:latest` voi `context: .`, `file: ./Dockerfile`.
- Job `deploy-vps` (needs `build-image`): dung `appleboy/ssh-action@v1.0.3` chay:
  ```bash
  docker pull ghcr.io/<owner>/<repo>:latest
  docker stop app || true && docker rm app || true
  docker run -d --name app -p 8080:8080 ghcr.io/<owner>/<repo>:latest
  ```

## Kiem tra
1. Push len `main`, mo tab **Actions** xem log `Deploy to VPS via SSH`.
2. Tren trinh duyet: `http://<VPS_IP>:8080/actuator/health` phai tra `{"status":"UP"}` (HTTP 200).
3. Chup man hinh log SSH Deploy + health check de nop bai.

## Chay local
```bash
./gradlew test
docker build -t session12-ex4:local .
docker run -d --name app -p 8080:8080 session12-ex4:local
curl http://localhost:8080/actuator/health
```

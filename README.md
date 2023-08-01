Инструкция по установке

0. Запустить minikube & прописать в /etc/hosts -> minikube ip arch.homework

1. Клонировать репозиторий проекта
git clone git@github.com:themuvarov/homework3.git

2. перейти в директорию с манифестами
cd homework3/k8s

3 установить манифесты
minikube kubectl -- apply -f .

4 Дождаться запуска подов 
minikube kubectl -- get pod

5 Запустить Postman с коллекцией из проекта (в аттаче тоже есть)
Postman ../dz3.postman_collection.json

6 CRUD для users

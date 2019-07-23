# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)

# Build and Run

* Run SpringBoot
```
gradle bootRun
```
* Generate WAR File
```
gradle bootWar
```
# Usage
### Access secure resource with token
```sh
curl -i http://localhost:9191/api/v1/me

{"error":"unauthorized","error_description":"Full authentication is required to access this resource"}
```

### Fetching access_token and refresh_token
```sh
curl -u rajithapp:secret -X POST localhost:9191/api/v1/oauth/token\?grant_type=password\&username=admin\&password=admin

{
    "access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjM4NTAwODMsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYThmYjQyNjAtYjY3Ni00NjY4LTlkYjgtOWVkZTAxYmY5ZmI1IiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.WgamLtsHhPDJ7k14nPxfe6i8LilG6rXV9UB1W-aBcQEsHo0cJzw9zSoH25zVdeq_ky7vdVLduY2r2wBtFgikpktlwX462rM9YrZih3kPJkcVrNznfJ7AOuLs5LwDLrJC1uL05Om1IX-8NP7z1Q-wrsPmAGKubkq_TIvRSmF-eeyc-t3MSmq9v_NQG6yFgFZKJT6GNQC9pwhC1Zw5dQa6Lm196JCnIda1gj0Hzxlhz9VBV7433aC411qx4nEMTCm7syjG8nUJ1cA7jF-NlbmTuvyfotq3eyd5InOex40RucgsaU6atdlWiVR4UnsUyKDBdcUcTIhb_Eme1nt5gXKmjQ",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImE4ZmI0MjYwLWI2NzYtNDY2OC05ZGI4LTllZGUwMWJmOWZiNSIsImV4cCI6MTU2NjQ0MTc4MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjdjM2EwYTYyLWRmNDEtNGVkMS1hOTg0LTAyMjRmYWFhOWZlZiIsImNsaWVudF9pZCI6ImNsaWVudElkIn0.kANS5O8vqnew7mQyYgYpZdkuGCdNzpcHQZwMfIvGQ3BHhElFMSbKrvWeuVslzh6VHYb0LTt32LwLDjBalljUxkLvRVn9feTcbIQFNI6-keUqXN54EzNEwKrsRX6ysNSszh1u3V_Y3DHlr6frge3Msz1pyeQl4U1Qn-VzJfBGtoLEZqzQDbWF2kAN_o8OBINQsv4zaGKPrkxgR5sd5UrQndzWLfFe7lcuEfQo84f-W6J_EHOaWpPo2gD2oMapnxeO6frhhctpWu9j7w1DOhRctmZU8KAEB7oYF-rGSGLQOh2OoHNG0cofYmBpGJLibX5NLDwTTrh4Ua4DkIOacEXf6w",
    "expires_in":1799,
    "scope":"read write",
    "jti":"a8fb4260-b676-4668-9db8-9ede01bf9fb5"
}
```

### Fetching acess_token by submitting refresh_token
```sh
curl -u rajithapp:secret -X POST 'http://localhost:9191/api/v1/oauth/token?grant_type=refresh_token&refresh_token=<refresh_token>'

{
    "access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjM4NTA2NjAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNzBiOTFhNjAtMmE3Yi00OTc2LTg2ZWItNDAzODUzYjg1Yjc2IiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.VpPuaHgxPl8Q1AzSd3-ntAnqgRYymrfLKUvgjab06G74Bhp2YYr0h8vmx2115VTDJb7qaXeXkFTH-GKQSx9YJ5zC8Jr_3z75KtlnxbrvgrIcX3QxUbvpSJScMXXt5LK5Rw-z4BLk-eICFeSns4F-DW6DFWjm__HOFjzkXmGjjDrXk-vT-1UHw-pNbsKYGRo0HadWQ6VfN7l2a9tQrfjKzpACp94C8yCRbU4aBd7MyEbGB_YH5ilOuxYFc3RzS5DMHzM94nAyuoOuvtieKNXp9lPIMECQ0QgrX4pPQ-hUbSrJxG2VVl0CB61ZaFFl66kEk8ZHs3QVUytYEX079E7jrA",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjcwYjkxYTYwLTJhN2ItNDk3Ni04NmViLTQwMzg1M2I4NWI3NiIsImV4cCI6MTU2NjQ0MjIwMCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjczZGJkZTU3LWY5MGQtNDUzMi05MzhkLTlkNmM0Yzc1NGZjZiIsImNsaWVudF9pZCI6ImNsaWVudElkIn0.MD3QT-DbT7Ud47Ed3AzOYnr0_WHZ0995hE43vpy4sVnZJ6sMKRORDAAa7N0GP6TKANRKXQX-r1PHk2tn0Hp9VHEzC8tyWvI1gbyhyb_tCHDDH8-PAvSlV_OZNGa89fbKgKPzDRDEMjl0ennPi5Qe3EFQ_PstSOlNFlA2bHVaLnHqaUIZjtA0aM2onI2g5dDIEp7YZtsDbC2mLBfOqeF5UpGhbnc7rxs6xLPoE2d08FpZb2aGe8NvdSzGO5XhaDx6mhE7st8TqjwUytgPROfL6Xsefc9TSc0w3KZVWOUajgREQiuq3UNvY7eQPFYP3sJ7Hs7Pzu0e9D5T5JWiFaO5rw",
    "expires_in":1799,
    "scope":"read write",
    "jti":"70b91a60-2a7b-4976-86eb-403853b85b76"
}
```

### Access secure resource sucessfully
```sh
curl localhost:9191/api/v1/hello -H "Authorization: Bearer <access_token>"

Hello! user
```
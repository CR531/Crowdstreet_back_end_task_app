Steps to run the appication.
1) Clone the repository into local system i.e. Crowdstreet_back_end_task_app
2) Also, clone the second repository into the system i.e. Crowdstreet_back_end_task_service
3) SampleApplication runs on port:8090, and SampleService runs on port:8091
4) Now, send the POST request from the SampleApplication using URL: https://localhost:8090/request with the body as { "body" : "Any message" }
5) As we have stubbed the data, we are using 10 second timeframe to process the request which includes SQL or any database layer in real-world.
6) Here, SampleApplication send the request to SampleService with a body string and a callback URL.
7) SampleService process the request and logs the operations and sends back the status to SampleApplication using the callback url with specific ID.
8) SampleApplication will receieve the status of the operation from the SampleService and the operations are seen in the logs.

9) Using GET call we can get the current status of the operation using URL: http://localhost:8090/status/1

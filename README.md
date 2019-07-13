# RevolutBanking

**Revolut Banking System (RBS)**

**Scope:** **(MVP)**

1. RBS should allow a Person to create a User with RBS.
2. RBS MUST allow a user to create one or multiple accounts.
3. RBS MUST allow a user (registered) with an Account to do money transactions
within RBS. That means transfer money between one RBS account to another RBS
 account.
4. RBS MUST as banking system to receive money from other Banks or Banking 
Like systems.
5. RBS MUST allow Account holder to view information related to the account 
and the user.
6. RBS MUST allow creation of admin user.
7. Admin users can block other users of the RBS based on some criteria and 
behaviour.


**Non-Functional Requirement**
1. RBS as system will be framework agnostic.
2. RBS MUST have high throughput and low latency.
3. RBS MUST always be available.

**Few technical deductions:**
1. The problem is not as simple of reading and writing data to an account 
(data element).
2. The system cannot provide high throughput without multiple threads.
3. Multiple threads will bring additional problems into the system (thread 
compete with each other for execution). (side node: threads on multicore will
 cost more, as they will do context switching on operating system (CPU level)
 , 20% of time is waisted only in doing context switching).
4. Synchronization on threads will block other threads.

**System Goals: Design Approach:**
1. We can deduce from the scope that there will be two type of request, one 
which reads data and another mutates data. And for our use case read (view 
balance) and mutation (credit or debit money to/from account) are connected. As 
read compete with writes based on how we have scoped our requirements. We need to 
pick an approach, options are: The system is highly consistent or highly 
available.
2. We will go by High Availability, all request (ex: view account balance or 
credit/debit) will receive a response and no read or write fails.
3. We will make our account Eventually consistent with the operations 
performed on it.
4. Eventual consistent account means, all mutation operations are done based on 
TIME when the operation was requested/done by an external system or RBS user.

**Technical Details:**

**Frameworks:**
1. None.

**Server (Tomcat/Jetty ??)**
1. None - Have build my own Minimum Web Container.

**Libraries:**
1. Jackson - For String to POJO and POJO to String.
2. Reflections - for API Delegation.
3. In-build Java Maps for Model.
4. In-build Java Multi-threading for handling multiple request.

**Build Tools**
1. Maven - For building the project.

![High Level Design](https://user-images.githubusercontent.com/3115397/61073406-fe1acc80-a432-11e9-9e78-74d13f6c50eb.png)

**Data Model**

![Data Model](https://user-images.githubusercontent.com/3115397/61170446-bb6a0900-a586-11e9-9dc9-c1bf5cc8d916.png)


**Running the junit cases:**
mvn clean install 

**Starting the server**
mvn compile

The server will start at 8080.

**Steps to execute Parallel integration Test cases: (Mimicking Production Like behaviour)**

1. Start the server:  mvn compile.
2. Create accounts: ./addAccount.sh
3. Use the created account (there id's) to credit funds to them: ./credit.sh
4. Use the same or different account ids to debit funds: ./debit.sh
5. Transfer funds from one account to another: ./transfer.sh
6. View balance for individual accounts: ./viewAccount.sh

For all what is happening you can check logs for all the scripts executed and
 also server logs.

**You can checkout the video for more details:**
Youtube Link: https://www.youtube.com/watch?v=beiVZvVVn-s&feature=youtu.be
 
(This will run all test cases and parallel test cases for 
10 min - you can increase the time by changes the sleep time in test case (BankingServiceTest))


**Sequence Diagrams**

**Server**

![Server](https://user-images.githubusercontent.com/3115397/61119626-e0904600-a4b8-11e9-86dc-6e50612dbbf4.png)

**View Balance:**
![viewBalance](https://user-images.githubusercontent.com/3115397/61118965-7aef8a00-a4b7-11e9-8c96-ff55d084ec37.png)

**Credit Funds**
![Credit](https://user-images.githubusercontent.com/3115397/61118960-7a56f380-a4b7-11e9-85d0-5dccd909f814.png)

**Debit Funds**
![debit](https://user-images.githubusercontent.com/3115397/61118961-7a56f380-a4b7-11e9-93cc-36f7d81bd547.png)

**Transfer Funds**
![transfer](https://user-images.githubusercontent.com/3115397/61118964-7aef8a00-a4b7-11e9-8e12-e160785b4601.png)

**Transaction Status**
![transactionStatus](https://user-images.githubusercontent.com/3115397/61118962-7aef8a00-a4b7-11e9-9b93-97283f3f00c4.png)

**Block Account**
![blockAccount](https://user-images.githubusercontent.com/3115397/61118958-7a56f380-a4b7-11e9-89bf-f78a3885d48a.png)

**Activate Account**
![activateAccount](https://user-images.githubusercontent.com/3115397/61118954-79be5d00-a4b7-11e9-8e7e-7dd1a44488e4.png)

**API Delegate/Executor**

![ApiExectorSequence](https://user-images.githubusercontent.com/3115397/61118955-79be5d00-a4b7-11e9-9204-4db2536c9eaf.png)

**Queuing System**

![Queuing System](https://user-images.githubusercontent.com/3115397/61120497-bccdff80-a4ba-11e9-8d96-247758d40306.png)

**Test Credit**

![testCredit](https://user-images.githubusercontent.com/3115397/61120678-24844a80-a4bb-11e9-9064-5b1256a4d379.png)

**Test Debit**

![testDebitParallel](https://user-images.githubusercontent.com/3115397/61120693-2f3edf80-a4bb-11e9-8cac-ac32acac7b27.png)

**Test Credit Parallel**

![testCreditParallel](https://user-images.githubusercontent.com/3115397/61121047-e63b5b00-a4bb-11e9-8157-913410a6b695.png)

**Test Debit Parallel**

![testDebitParallel](https://user-images.githubusercontent.com/3115397/61121048-e63b5b00-a4bb-11e9-9992-8bb8a332161c.png)

**Test Transfer Parallel**

![TestTransfer](https://user-images.githubusercontent.com/3115397/61121049-e63b5b00-a4bb-11e9-9188-7f3d6f54ad42.png)

**Test Transfer**

![testTransferAccountParallel](https://user-images.githubusercontent.com/3115397/61121050-e6d3f180-a4bb-11e9-9e55-21faa20aa173.png)

**View Balance Parallel**

![viewAccountParallel](https://user-images.githubusercontent.com/3115397/61121052-e6d3f180-a4bb-11e9-9741-2acd6787fdbc.png)


**Improvement on Design:**
 
 The current design solves the problem and is scalable in the scope of the 
 problem.
 
 1. Is the system 100% scalable for multi multi million request: NO.
 2. There is a single thread to execute from the queue. Which makes the system
 dependent on a single thread to execute all transactions.
 3. The improvement we can do is TO have multiple threads which reads from queue
 and execute (lets say 50 threads).
 4. But this bring complexity, the complexity here is we need to execute 
 transactions in time, example: request are coming in the below fashion. 
 
 CA1,DA1,CA2,CA2,DA2,T(1,2),T(2,1)...........CA(n),DA(n),T(n,n)
 
 CA1 = Credit funds to account 1.
 DA1 = Debit funds from account 1.
 T1 = Transfer between two accounts
 
 THE CORRECTNESS OF THE SYSTEM IS THE MOST IMPORTANT THING IN BANKING SYSTEM.
 
 If two thread executes two different types of transactions for the same account,
 we should not allow meaning Credit came first and debit later for account1. It
 should happen in the same (sequence) time manner.
 
 **How do we solve this problem:**
 1. We need two queues, Primary Queue and Priority Queue (this is not java 
 PriorityQueue).
 2. We should have a process lock module which takes lock on account when it 
 executes a request and not allow other threads to execute any other request 
 (specially debit or transfer from account1) on the same account.
 3. If a thread picks such request it should add it into priority queue.
 4. always priority queue transactions should get picked for execution first 
 and  then other transactions from primary queue should get picked executed.
 5. We can improve this solution more and say sequential credits requests can 
 execute in parallel and have a different kind of process lock which says
     i. ProcessLock.CREDIT(5) = ProcessLock for credit on any account with 5 
     parallel request.
     ii. ProcessLock.DEBIT(1) = ProcessLock for debit on any account with 1 
     thread execution.
     iii. ProcessLock.Transfer(1) = ProcessLock for transfer on any account 
     with 1 thread execution.
 
   **Please find the design (IF NOT CLEAR WE CAN DISCUSS THIS..)**
   
   **Desing Improvements**
   ![Design Improvemtns](https://user-images.githubusercontent.com/3115397/61146664-2ddfd800-a4f8-11e9-92f4-9e84fe4c5c57.png)




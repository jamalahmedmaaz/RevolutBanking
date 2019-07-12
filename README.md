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

**Sequence Diagrams**

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

**API Deledgator/Executor**
![ApiExectorSequence](https://user-images.githubusercontent.com/3115397/61118955-79be5d00-a4b7-11e9-9204-4db2536c9eaf.png)


**Running the junit cases:
mvn clean install 

(This will run all test cases and parallel test cases for 
10 min - you can increase the time by changes the sleep time in test case (BankingServiceTest))

**Starting the server**
mvn compile

The server will start at 8080.

**Executing API Test Cases:**
There are scripts which are provided which can used to execute request 
parallelly.
1. Creation of accounts (addAccount.sh)
2. Crediting to account (credit.sh)
3. Debiting from account (debit.sh)
4. Transferring funds between two accounts (transfer.sh)
5. Viewing balance (viewAccount.sh)

All these scripts executes infinitely.

**Examples:**

**Adding account.** (Please see the screenshots)
./addAccount.sh 

**Credit an account**
./credit.sh

**Debit an account**
./debit.sh

**Transfer Funds**
./transfer.sh

**View account**
./viewAccount.sh


Steps:
1. Create few accounts.
2. Use credit.sh to add funds to any account you wish.
3. Use debit.sh to debit funds from any account you wish.
4. If you have created multiple accounts, you can transfer funds between them.
5. You can continuously view the account balance.

For all what is happening you can check logs for all the scripts executed and
 also server logs.

**You can checkout the video for more details:**
Youtube Link: https://www.youtube.com/watch?v=_AdrX2uCvBE&feature=youtu.be





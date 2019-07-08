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
use master
go
create database PDA
go
use PDA
go
CREATE TABLE Contacts
(
	[S_no] [int] IDENTITY(1,1) NOT NULL CONSTRAINT [PK_Contacts] PRIMARY KEY ,
	[Name] [varchar](30)  NOT NULL,
	[Address] [varchar](50) NOT NULL,
	[EmailID] [varchar](50) NULL,
	[phone] [char](14) NOT NULL,
	[DOB] [datetime] NOT NULL,
	[AnnDate] [datetime] NULL,
 )
go
CREATE TABLE Login
(
	[loginid] [varchar](10) not NULL CONSTRAINT [PK_login] PRIMARY KEY,
	[password] [varchar](10) not NULL
) 
go
insert into Login values('DBA','password')
go
CREATE TABLE Notes
(
	[S_No] [int] IDENTITY(1,1) NOT NULL CONSTRAINT [PK_Notes] PRIMARY KEY ,
	[Date] [datetime] NOT NULL,
	[NoteName] [varchar](20) NOT NULL,
	[Notes] [varchar](1000) NOT NULL
)
go
CREATE TABLE ToDoList
(
	[s_no] [int] IDENTITY(1,1) NOT NULL  CONSTRAINT [PK_todolist] PRIMARY KEY ,
	[Date] [datetime] not NULL,
	[Task] [varchar](20) not NULL,
	[Status] [bit] NULL
)
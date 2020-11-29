SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](20) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[created] [datetime] NULL,
	[coin] [int] NULL,
	[exp] [int] NULL,
	[lv] [int] NULL,
	[avatar] [varchar](500) NULL,
	[giftdate] [date] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[users] ADD PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (getdate()) FOR [created]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((100)) FOR [coin]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [exp]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [lv]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [avatar]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ('2020-11-26') FOR [giftdate]
GO
insert into users(username, password, email, name) VALUES('admin', '12345678', 'admin@gmail.com', 'adminABC')
insert into users(username, password, email, name) VALUES('abc', '12345678', 'abc@gmail.com', 'hello')
insert into users(username, password, email, name, coin) VALUES('user1', '12345678', 'user1@gmail.com', 'hello1', 200)
insert into users(username, password, email, name, coin) VALUES('user2', '12345678', 'user2@gmail.com', 'hello2', 300)
insert into users(username, password, email, name, coin) VALUES('user3', '12345678', 'user3@gmail.com', 'hello3', 400)
insert into users(username, password, email, name, coin) VALUES('khai', '12345678', 'khailuong61@gmail.com', 'KHAI', 500)
insert into users(username, password, email, name, coin) VALUES('user4', '12345678', 'user4@gmail.com', 'hello4', 300)
insert into users(username, password, email, name, coin) VALUES('user5', '12345678', 'user5@gmail.com', 'hello5', 800)
insert into users(username, password, email, name, coin) VALUES('user6', '12345678', 'user6@gmail.com', 'hello6', 8000)
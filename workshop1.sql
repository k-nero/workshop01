USE [master]

CREATE DATABASE [workshop01]

USE [workshop01]
GO
/****** Object: Table [dbo].[tbl_Mobile] Script Date: 2/24/2026 10:31:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Mobile](
[mobileId] [nvarchar](50) NOT NULL,
[description] [text] NOT NULL,
[price] [float] NULL,
[mobileName] [nchar](20) NOT NULL,
[yearOfProduction] [int] NULL,
[quantity] [int] NULL,
[notSale] [bit] NOT NULL,
CONSTRAINT [PK_mobileId] PRIMARY KEY CLUSTERED
(
[mobileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object: Table [dbo].[tbl_User] Script Date: 2/24/2026 10:31:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
[userId] [nvarchar](50) NOT NULL,
[password] [bigint] NOT NULL,
[fullName] [nvarchar](50) NOT NULL,
[role] [int] NULL,
CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED
(
[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
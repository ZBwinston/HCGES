#Unit表的删除语句：

DELETE FROM Unit;

#Employee表的删除语句：

DELETE FROM Employee;

#Package表的删除语句：

DELETE FROM Package;

#Physical_exam表的删除语句：

DELETE FROM Physical_exam;

#Balance_record表的删除语句：

DELETE FROM Balance_record;

#Physical_exam_item表的删除语句：

DELETE FROM Physical_exam_item;

#Physical_exam_complex表的删除语句：

DELETE FROM Physical_exam_complex;

#Physical_exam_conclusion表的删除语句：

DELETE FROM Physical_exam_conclusion;

#Physical_exam_summary表的删除语句：

DELETE FROM Physical_exam_summary;

#Physical_exam_report表的删除语句：

DELETE FROM Physical_exam_report;

#User表的删除语句：

DELETE FROM User;

#Doctor_information表的删除语句：

DELETE FROM Doctor_information;

#Examinee_information表的删除语句：

DELETE FROM Examinee_information;

#Project_information表的删除语句：

DELETE FROM Project_information;

#Examination_record表的删除语句：

DELETE FROM Examination_record;

#Medical_conclusion表的删除语句：

DELETE FROM Medical_conclusion;

#Medical_summary表的删除语句：

DELETE FROM Medical_summary;

#Report表的删除语句：

DELETE FROM Report;

#Role表的删除语句：

DELETE FROM Role;

#User_Role_relationship表的删除语句：

DELETE FROM User_Role_relationship;

#Menu表的删除语句：

DELETE FROM Menu;

#Department表的删除语句：

DELETE FROM Department;

#Detail_item表的删除语句：

DELETE FROM Detail_item;

#Package_Project_relationship表的删除语句：

DELETE FROM Package_Project_relationship;

#Project_DetailItem_relationship表的删除语句：

DELETE FROM Project_DetailItem_relationship;

#Log表的删除语句：

DELETE FROM Log;

#Parameter表的删除语句：

DELETE FROM Parameter;

#创建Unit表：

CREATE TABLE Unit (
  ID varchar(50) PRIMARY KEY,
  Unit_name varchar(100),
  Contact_person varchar(50),
  Phone varchar(20),
  Account_balance float
);

#创建Employee表：

CREATE TABLE Employee (
  ID varchar(50) PRIMARY KEY,
  Name varchar(50),
  Gender varchar(10),
  Age int,
  Phone varchar(20),
  ID_card_number varchar(20),
  Billing_status varchar(20),
  Unit_ID varchar(50),
  FOREIGN KEY (Unit_ID) REFERENCES Unit(ID)
);

#创建Package表：

CREATE TABLE Package (
  ID varchar(50) PRIMARY KEY,
  Package_name varchar(100),
  Price float
);

#创建Physical_exam表：

CREATE TABLE Physical_exam (
  ID varchar(50) PRIMARY KEY,
  Unit_ID varchar(50),
  Employee_ID varchar(50),
  Exam_time datetime,
  Package_ID varchar(50),
  Payment_method varchar(20),
  FOREIGN KEY (Unit_ID) REFERENCES Unit(ID),
  FOREIGN KEY (Employee_ID) REFERENCES Employee(ID),
  FOREIGN KEY (Package_ID) REFERENCES Package(ID)
);

#创建Balance_record表：

CREATE TABLE Balance_record (
  ID varchar(50) PRIMARY KEY,
  Unit_ID varchar(50),
  Action_type varchar(20),
  Amount float,
  Action_time datetime,
  FOREIGN KEY (Unit_ID) REFERENCES Unit(ID)
);

#创建Physical_exam_item表：

CREATE TABLE Physical_exam_item (
  ID varchar(50) PRIMARY KEY,
  Item_name varchar(100),
  Price float
);

#创建Physical_exam_complex表：

CREATE TABLE Physical_exam_complex (
  ID varchar(50) PRIMARY KEY,
  Physical_exam_ID varchar(50),
  Item_ID varchar(50),
  Item_result varchar(100),
  FOREIGN KEY (Physical_exam_ID) REFERENCES Physical_exam(ID),
  FOREIGN KEY (Item_ID) REFERENCES Physical_exam_item(ID)
);

#创建Physical_exam_conclusion表：

CREATE TABLE Physical_exam_conclusion (
  ID varchar(50) PRIMARY KEY,
  Physical_exam_ID varchar(50),
  Conclusion_content varchar(100),
  FOREIGN KEY (Physical_exam_ID) REFERENCES Physical_exam(ID)
);

#创建Physical_exam_summary表：

CREATE TABLE Physical_exam_summary (
  ID varchar(50) PRIMARY KEY,
  Physical_exam_ID varchar(50),
  Summary_content varchar(100),
  FOREIGN KEY (Physical_exam_ID) REFERENCES Physical_exam(ID)
);

#创建Physical_exam_report表：

CREATE TABLE Physical_exam_report (
  ID varchar(50) PRIMARY KEY,
  Physical_exam_ID varchar(50),
  Report_name varchar(100),
  Report_content varchar(100),
  FOREIGN KEY (Physical_exam_ID) REFERENCES Physical_exam(ID)
);

#创建User表：

CREATE TABLE User (
  ID varchar(50) PRIMARY KEY,
  Username varchar(50),
  Password varchar(50),
  Real_name varchar(50),
  Department varchar(50)
);

ALTER TABLE User CHANGE Department State VARCHAR(2) DEFAULT 1;

#创建Doctor_information表：

CREATE TABLE Doctor_information (
  ID varchar(50) PRIMARY KEY,
  Doctor_name varchar(50),
  Username varchar(50),
  Password varchar(50),
  Department varchar(50)
);

#创建Examinee_information表：

CREATE TABLE Examinee_information (
  ID varchar(50) PRIMARY KEY,
  Examinee_name varchar(50),
  Gender varchar(20),
  Age int,
  ID_card_number varchar(20)
);

#创建Project_information表：

CREATE TABLE Project_information (
  ID varchar(50) PRIMARY KEY,
  Project_name varchar(100),
  Department_name varchar(50),
  Project_type varchar(50),
  Price float
);

#创建Examination_record表：

CREATE TABLE Examination_record (
  ID varchar(50) PRIMARY KEY,
  Examinee_ID varchar(50),
  Doctor_ID varchar(50),
  Project_ID varchar(50),
  Reception_time datetime,
  Completion_time datetime,
  Signature varchar(50),
  FOREIGN KEY (Examinee_ID) REFERENCES Examinee_information(ID),
  FOREIGN KEY (Doctor_ID) REFERENCES Doctor_information(ID),
  FOREIGN KEY (Project_ID) REFERENCES Project_information(ID)
);

#创建Medical_conclusion表：

CREATE TABLE Medical_conclusion (
  ID varchar(50) PRIMARY KEY,
  Examinee_ID varchar(50),
  Doctor_ID varchar(50),
  Project_ID varchar(50),
  Conclusion_type varchar(50),
  Conclusion_content varchar(100),
  Submission_time datetime,
  FOREIGN KEY (Examinee_ID) REFERENCES Examinee_information(ID),
  FOREIGN KEY (Doctor_ID) REFERENCES Doctor_information(ID),
  FOREIGN KEY (Project_ID) REFERENCES Project_information(ID)
);

#创建Medical_summary表：

CREATE TABLE Medical_summary (
  ID varchar(50) PRIMARY KEY,
  Examinee_ID varchar(50),
  Conclusion_content_from_doctors_in_each_department varchar(100),
  Summary_content varchar(100),
  Suggestions varchar(100),
  Lifestyle_guidance varchar(100),
  Submission_time datetime,
  FOREIGN KEY (Examinee_ID) REFERENCES Examinee_information(ID)
);

#创建Report表：

CREATE TABLE Report (
  ID varchar(50) PRIMARY KEY,
  Examinee_ID varchar(50),
  General_doctor_ID varchar(50),
  Report_content varchar(100),
  Generation_time datetime,
  FOREIGN KEY (Examinee_ID) REFERENCES Examinee_information(ID),
  FOREIGN KEY (General_doctor_ID) REFERENCES Doctor_information(ID)
);

#创建Role表：

CREATE TABLE Role (
  ID varchar(50) PRIMARY KEY,
  Role_name varchar(50),
  Note varchar(100)
);

#创建User_Role_relationship表：

CREATE TABLE User_Role_relationship (
  ID varchar(50) PRIMARY KEY,
  User_ID varchar(50),
  Role_ID varchar(50),
  FOREIGN KEY (User_ID) REFERENCES User(ID),
  FOREIGN KEY (Role_ID) REFERENCES Role(ID)
);

#创建Menu表：

CREATE TABLE Menu (
  ID varchar(50) PRIMARY KEY,
  Menu_name varchar(50),
  Parent_menu_ID varchar(50),
  Menu_icon varchar(50),
  Menu_link varchar(100)
);

-- ALTER TABLE Menu ADD INDEX menu_id_index (ID);

#创建一个索引
CREATE INDEX menu_index ON menu(Parent_menu_ID);

#创建Role_menu表
CREATE TABLE Role_menu (
  ID varchar(50) PRIMARY KEY,
  Role_ID varchar(50),
  Menu_PID varchar(50),
  FOREIGN KEY (Role_ID) REFERENCES Role(ID),
  FOREIGN KEY (Menu_PID) REFERENCES Menu(ID) 
);


#创建Department表：
CREATE TABLE Department (
  ID varchar(50) PRIMARY KEY,
  Department_name varchar(50),
  Manager varchar(50),
  Department_introduction varchar(100)
);

# 创建Department_project表
CREATE TABLE Department_project(
  ID VARCHAR(50) PRIMARY KEY,
	Department_ID varchar(50),
  Project_ID varchar(50),
  FOREIGN KEY (Department_ID) REFERENCES department(ID),
  FOREIGN KEY (Project_ID) REFERENCES Project_information(ID)
);

#创建Detail_item表：

CREATE TABLE Detail_item (
  ID varchar(50) PRIMARY KEY,
  Detail_item_name varchar(50),
  Measurement_unit varchar(50),
  Upper_limit_value float,
  Lower_limit_value float
);

#创建Package_Project_relationship表：

CREATE TABLE Package_Project_relationship (
  ID varchar(50) PRIMARY KEY,
  Package_ID varchar(50),
  Project_ID varchar(50),
  FOREIGN KEY (Package_ID) REFERENCES Package(ID),
  FOREIGN KEY (Project_ID) REFERENCES Project_information(ID)
);

#创建Project_DetailItem_relationship表：

CREATE TABLE Project_DetailItem_relationship (
  ID varchar(50) PRIMARY KEY,
  Project_ID varchar(50),
  Detail_item_ID varchar(50),
  FOREIGN KEY (Project_ID) REFERENCES Project_information(ID),
  FOREIGN KEY (Detail_item_ID) REFERENCES Detail_item(ID)
);

#创建Log表：

CREATE TABLE Log (
  ID varchar(50) PRIMARY KEY,
  Action_time datetime,
  Operator varchar(50),
  Action_type varchar(50),
  Action_content varchar(100)
);

#创建Parameter表：

CREATE TABLE Parameter (
  ID varchar(50) PRIMARY KEY,
  Parameter_name varchar(50),
  Parameter_value varchar(50),
  Parameter_type varchar(50)
);


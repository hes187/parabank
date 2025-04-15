# 🧪 Fake Registration Database - Demoblaze

This SQL script is a fully testable fake database for validating the **User Registration** feature of the Demoblaze demo website.

---

## 📄 File: `registration_db.sql`

### 📋 Description:
This file contains:
- SQL commands to create a `users` table
- Sample user data
- Insert and select queries that simulate real registration scenarios

---

## ✅ What's Included

### Table Structure:
- `id`: Primary Key
- `username`: Unique, Required
- `password`: Required
- `email`: Optional
- `created_at`: Auto timestamp

---

## 🧪 Test Scenarios Covered

| Test Case ID  | Description                                           |
|---------------|-------------------------------------------------------|
| TC_DB_001     | Verify a registered user can be selected              |
| TC_DB_002     | Insert a duplicate username (Expected: Fail)          |
| TC_DB_003     | Register a new unique user                            |
| TC_DB_004     | Verify new user appears in database                   |
| TC_DB_005     | Attempt empty username (Expected: Fail)               |
| TC_DB_006     | Attempt NULL password (Expected: Fail)                |
| TC_DB_007     | Check auto-generated created_at timestamp             |

---

## 🔗 Try it Online

You can run and test this script on:  
[dbfiddle.uk](https://dbfiddle.uk/zudNr5zF)

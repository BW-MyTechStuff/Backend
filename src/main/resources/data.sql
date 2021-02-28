DELETE
FROM users;

DELETE
FROM items;

DELETE
FROM userrole;

DELETE
FROM itemstatus;

INSERT INTO ITEMSTATUS (itemstatusid, itemstatustype)
VALUES (1, 'Available'),
       (2, 'Currently Not Available For Rent');

INSERT INTO USERROLE (userroleid, userroletype)
VALUES (1, 'OWNER'),
       (2, 'RENTER');

INSERT INTO USERS (userid, userroleid, username, fname, lname, email, password, createdby, createddate, lastmodifiedby,
                   lastmodifieddate)
VALUES (1, 1, 'Hussain', 'Hussain', 'Ali', 'hussain@gmail.com', '$2y$12$60LHaEM0yTTNtawNE/.GCOQqjlSHURGIEZRblhBV/LmAQpddStbx2', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (2, 2, 'Leon',  'Leon', 'Nasswetter', 'Leon@gmail.com','password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (3, 1, 'Tara', 'Tara', 'T', 'Tara@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (4, 2, 'Jason', 'Jason', 'Corchado', 'Jason@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP);

INSERT INTO ITEMS (itemid, userid, itemname, itemdescription, itemcostperday, itemstatusid, numberofdaysrented,
                   createdby, createddate, lastmodifiedby, lastmodifieddate)
VALUES (1, 1, 'Speaker', 'Bluetooth speaker', 25.00 , 1, 0, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
       (2, 3, 'Macbook', 'Macbook Pro 15inch', 100.00 , 1, 0, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

/*
We must tell hibernate the ids that have already been used.
The number must be larger than the last used id.
10 > 4 so we are good!
 */

alter sequence hibernate_sequence restart with 10;
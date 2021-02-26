DELETE
FROM USERS;

DELETE
FROM ITEMS;

DELETE
FROM USERROLE;

DELETE
FROM ITEMSTATUS;

INSERT INTO ITEMSTATUS (itemstatusid, itemstatustype)
VALUES (1, 'Available'),
       (2, 'Currently Not Available For Rent');

INSERT INTO USERROLE (userroleid, userroletype)
VALUES (1, 'OWNER'),
       (2, 'RENTER');

INSERT INTO USERS (userid, userroleid, username, email, password, createdby, createddate, lastmodifiedby,
                   lastmodifieddate)
VALUES (1, 1, 'Hussain', 'hussain@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (2, 2, 'Leon', 'Leon@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (3, 1, 'Tara', 'Tara@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP),
       (4, 2, 'Jason', 'Jason@gmail.com', 'password', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM',
        CURRENT_TIMESTAMP);

INSERT INTO ITEMS (itemid, userid, itemname, itemdescription, itemcostperday, itemstatusid, createdby, createddate,
                   lastmodifiedby, lastmodifieddate)
VALUES (1, 1, 'Speaker', 'Bluetooth speaker', 25.00 , 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
       (2, 2, 'Macbook', 'Macbook Pro 15inch', 100.00 , 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

/*
We must tell hibernate the ids that have already been used.
The number must be larger than the last used id.
10 > 4 so we are good!
 */

alter sequence hibernate_sequence restart with 10;
CREATE TABLE application
(
    id SERIAL NOT NULL PRIMARY KEY,
    "timeofreceipt" Date,
    serviceTime INTEGER,
    profit INTEGER,
    model_id INTEGER
);

CREATE TABLE model
(
    id SERIAL NOT NULL PRIMARY KEY,
    clerkCount INTEGER,
    maxClientCount INTEGER,
    countOfServedClients INTEGER,
    countOfLostClients INTEGER,
    maxQueue INTEGER,
    minQueue INTEGER,
    averageQueue INTEGER,
    averageWaitTime INTEGER,
    profit INTEGER
);
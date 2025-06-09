CREATE TABLE seat (
    id INTEGER PRIMARY KEY,
    status VARCHAR(20) NOT NULL
);

INSERT INTO seat (id, status)
SELECT i, 'AVAILABLE' FROM generate_series(1, 25) AS s(i);

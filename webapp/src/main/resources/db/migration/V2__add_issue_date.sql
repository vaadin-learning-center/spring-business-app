ALTER TABLE issue ADD date DATE;
UPDATE issue SET date = '2018-09-25';
ALTER TABLE issue MODIFY date DATE NOT NULL;
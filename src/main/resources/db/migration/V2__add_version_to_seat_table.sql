ALTER TABLE seat
ADD COLUMN version BIGINT DEFAULT 0; -- Default to 0 for existing rows
INSERT IGNORE INTO order_type(id, name) values("cd", "Date Created");
INSERT IGNORE INTO order_type(id, name) values("dl", "Deadline");
INSERT IGNORE INTO order_type(id, name) values("nm", "Name");
INSERT IGNORE INTO order_type(id, name) values("st", "Status");

INSERT IGNORE INTO filter_type(id, name, object_type) values("st", "Status", "boolean");
INSERT IGNORE INTO filter_type(id, name, object_type) values("ex", "Expired", "boolean");
INSERT IGNORE INTO filter_type(id, name, object_type) values("nm", "Name", "string");

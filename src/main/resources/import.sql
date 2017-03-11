CREATE TABLE IF NOT EXISTS bookmark (
	id SERIAL,
  webpage VARCHAR(255),
  url VARCHAR(255)
);

INSERT INTO bookmark(webpage, url) VALUES('Onet', 'https://www.onet.pl');
INSERT INTO bookmark(webpage, url) VALUES('Wirtualna Polska', 'https://www.wp.pl');
INSERT INTO bookmark(webpage, url) VALUES('GitHub', 'https://www.github.com');
INSERT INTO bookmark(webpage, url) VALUES('Stack Overflow', 'https://www.stackoverflow.com');
INSERT INTO bookmark(webpage, url) VALUES ('Vaadin', 'https://vaadin.com');
INSERT INTO bookmark(webpage, url) VALUES ('Red Hat', 'https://www.redhat.com');
INSERT INTO bookmark(webpage, url) VALUES ('Canonical', 'http://www.canonical.com');
INSERT INTO bookmark(webpage, url) VALUES ('Sonatype', 'http://www.sonatype.com');
INSERT INTO bookmark(webpage, url) VALUES ('Alfresco', 'https://www.alfresco.com');
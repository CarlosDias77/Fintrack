ALTER TABLE usuarios ADD CONSTRAINT uk_cpf UNIQUE (cpf);
ALTER TABLE usuarios ADD CONSTRAINT uk_email UNIQUE (email);

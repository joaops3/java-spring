CREATE TABLE IF NOT EXISTS roles (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS permissions (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles_permissions (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    role_id UUID,
    permissions_id UUID,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permissions_id) REFERENCES permissions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role_id UUID,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- update user set name = 'admin' where id = '1';

-- update user set name = 'admin' from user join roles on user.role_id = roles.id where roles.name = 'admin';
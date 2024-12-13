create table user if not exists (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role_id UUID,
    foreign key (role_id) references roles(id) on delete CASCADE;
);

create table roles if not exists (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL
    roles_permissions_id UUID,
    foreign key (roles_permissions_id) references roles_permissions(id) on delete CASCADE;
);

create table roles_permissions if not exists {
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    role_id UUID,
    permissions_id UUID,
    foreign key (role_id) references roles(id);
    foreign key (permissions_id) references permissions(id);
}


create table permissions if not exists {
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL
}


update user set name = 'admin' where id = '1';

update user set name = 'admin' from user join roles on user.role_id = roles.id where roles.name = 'admin';
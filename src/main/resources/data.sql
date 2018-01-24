INSERT INTO role(name) VALUES ('GUEST');
INSERT INTO role(name) VALUES ('USER');
INSERT INTO role(name) VALUES ('ADMIN');

INSERT INTO resolution(resolution_Name) VALUES ('Fixed');
INSERT INTO resolution(resolution_Name) VALUES ('Invalid');
INSERT INTO resolution(resolution_Name) VALUES ('Wontfix');
INSERT INTO resolution(resolution_Name) VALUES ('Worksforme');

INSERT INTO status(status_name) VALUES ('New');
INSERT INTO status(status_name) VALUES ('Assigned');
INSERT INTO status(status_name) VALUES ('In Progress');
INSERT INTO status(status_name) VALUES ('Resolved');
INSERT INTO status(status_name) VALUES ('Closed');
INSERT INTO status(status_name) VALUES ('Reopened');

INSERT INTO priority(priority_name) VALUES ('Critical');
INSERT INTO priority(priority_name) VALUES ('Major');
INSERT INTO priority(priority_name) VALUES ('Important');
INSERT INTO priority(priority_name) VALUES ('Minor');

INSERT INTO type(type_name) VALUES ('Cosmetic');
INSERT INTO type(type_name) VALUES ('Bug');
INSERT INTO type(type_name) VALUES ('Feature');
INSERT INTO type(type_name) VALUES ('Performance');

INSERT INTO user(email, first_name, last_name, username, password) VALUES ('squidward@tut.by','Squidward', 'Tentacles','guest', 'guest');
INSERT INTO user(email, first_name, last_name, username, password) VALUES ('star@tut.by','Patrick', 'Star','user', 'user');
INSERT INTO user(email, first_name, last_name, username, password) VALUES ('spongebob@mail.ru','SpongeBob', 'SquarePants','admin', 'admin');

INSERT INTO users_roles(user_id, role_id) VALUES ('1','1');
INSERT INTO users_roles(user_id, role_id) VALUES ('2','2');
INSERT INTO users_roles(user_id, role_id) VALUES ('3','3');

INSERT INTO build(build_numb) VALUES ('1.1');
INSERT INTO build(build_numb) VALUES ('1.2');
INSERT INTO build(build_numb) VALUES ('1.3');
INSERT INTO build(build_numb) VALUES ('2.1');
INSERT INTO build(build_numb) VALUES ('2.2');
INSERT INTO build(build_numb) VALUES ('2.3');

INSERT INTO project(description, project_name,build_id, user_id) VALUES ('Quo et noster qualisque','Maiorum','1','1');
INSERT INTO project(description, project_name,build_id, user_id) VALUES ('Id est latine scripta ','Primis','2', '2');
INSERT INTO project(description, project_name,build_id, user_id) VALUES ('Ad ridens molestiae','Legimus','3','3');
INSERT INTO project(description, project_name,build_id, user_id) VALUES ('His nulla erroribus','Vivendum','4','1');
INSERT INTO project(description, project_name,build_id, user_id) VALUES ('Cu ignota primis', 'Nostrum','1','3');

INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('18.12.2017','Some description','Sea et nihil altera, ad mollis appetere consectetuer eum',
                     '1','2','1','1','1','1','1','1');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('18.12.2017','Lorem ipsum','Ea sit aliquip laboramus','2','3','1','3','2','2','3','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('22.01.2016','Dolor sit ame','Ancillae suscipit vix id, tota expetenda gloriatur',
                     '3','1','1','1','2','1','4','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('28.12.2017','Nec ne elit','Ut ius falli soleat abhorreant','2','3','1','1','3','1','4','1');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('01.07.2017','Sunum erat reprehendunt','Id eum admodum assentior complectitur','1','2','1','2','1','1','1','1');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('18.12.2017','Reprehendunt','Sit ame tre','2','3','1','2','2','2','3','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('5.01.2016','Dolor ame','Quo at nonumy dicunt efficiendi','3','1','2','1','2','1','4','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('24.12.2017','Nec elit','His cu legere nostrum','1','3','1','2','3','1','4','1');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('01.07.2017','Sunum erat reprehendunt','Detracto honestatis id nam','1','2','1','1','1','1','1','1');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('18.12.2017','Reprehendunt','Veniam tritani albucius ea pri','2','3','1','3','2','2','3','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('5.01.2016','Dolor ame','Eu audiam posidonium his','3','1','2','1','2','1','4','2');
INSERT INTO issue(create_date,description,summary, assignee_id,build_id,created_by_id,
                  priority_id,project_id,resolution_id,status_id,type_id)
VALUES ('24.12.2017','Nec elit','Ad iuvaret intellegam pro','1','3','1','2','3','1','4','1');


INSERT INTO `t_role` (`id`, `name`, `create_time`, `update_time`, `is_delete`, `op_user_id`, `op_user_name`) VALUES
	('bef774c73e044a9a84f8ee74277b4976', 'admin', now(), now(), 0, '7a1e401fa61a4eb8b210d2153d74ce9a', 'admin');
	
INSERT INTO `t_user` (`id`, `name`, `user_no`, `username`, `password`, `role_id`, `create_time`, `update_time`, `is_delete`, `op_user_id`, `op_user_name`) VALUES
	('7a1e401fa61a4eb8b210d2153d74ce9a', 'admin', 'admin', 'admin', '$2a$10$iRQLKxfsbOltFRnMhCkGk.SB74OfGv9wspgAqWlySrgcv5IlXYmQ2', 'bef774c73e044a9a84f8ee74277b4976', now(), now(), 0, '7a1e401fa61a4eb8b210d2153d74ce9a', 'admin');

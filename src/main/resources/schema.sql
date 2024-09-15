CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  
);

CREATE TABLE materials (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
);

CREATE TABLE purchase_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    status VARCHAR(50),
    order_date DATE,
    total DECIMAL(15, 2),
    user_id BIGINT,
    title VARCHAR(255),
    approval_workflow VARCHAR(50),
    current_approval_level VARCHAR(50),
    company VARCHAR(255),
    address VARCHAR(255),
    company_details TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for Approval Levels
CREATE TABLE approval_levels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    level INT,
    approver_id BIGINT,
    status VARCHAR(50),
    approver_role VARCHAR(255) NOT NULL,
    approval_step_number INT NOT NULL,
    purchase_order_id BIGINT,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_orders(id)
);

-- Table for Approval Workflows
CREATE TABLE approval_workflows (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Adjust the foreign key relationship in the ApprovalLevels table
ALTER TABLE approval_levels
ADD CONSTRAINT fk_approval_workflow
FOREIGN KEY (approval_workflow_id) REFERENCES approval_workflows(id);


-- Table for Purchase Order Items
CREATE TABLE purchase_order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT,
    unit_price DECIMAL(15, 2),
    total_amount DECIMAL(15, 2),
    purchase_order_id BIGINT,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_orders(id)
);

-- Table for Approval History
CREATE TABLE approval_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_order_id BIGINT,
    approval_level_id BIGINT,
    approved BOOLEAN,
    approved_date DATE,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_orders(id),
    FOREIGN KEY (approval_level_id) REFERENCES approval_levels(id)
);

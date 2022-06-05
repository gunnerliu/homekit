CREATE TABLE IF NOT EXISTS hk_iots(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    iot_code    VARCHAR(64) NOT NULL UNIQUE COMMENT 'iot设置code,唯一',
    iot_name    VARCHAR(64) COMMENT '设备名',
    ip_addr     VARCHAR(64) COMMENT 'IPV4 地址',
    device_type VARCHAR(64) COMMENT '设备类型',
    switch_value VARCHAR(64) COMMENT '开关值,ON->开、OFF->关',
    last_beat_time  DATETIME    COMMENT '上一次心跳时间,默认 30 秒一次心跳',
    online_state    VARCHAR(32)  DEFAULT 'OFFLINE'   COMMENT '在线状态,OFFLINE->离线,ONLINE->在线',
    cts         DATETIME    DEFAULT CURRENT_TIMESTAMP   COMMENT '创建时间',
    uts         DATETIME    DEFAULT CURRENT_TIMESTAMP   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'iot设备表';
package cn.itcast.order.service;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用Fein远程调用 （改变）
        User user = userClient.findById(order.getUserId());
        // 3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;


    }


/*    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplate发起Http请求，查询用户
        // 2.1url
        String url="http://userservice/user/"+order.getUserId();
        //发送url请求 反序列化User.class
        User user = restTemplate.getForObject(url, User.class);
        //封装user
        order.setUser(user);
        // 4.返回
        return order;


    }*/
}

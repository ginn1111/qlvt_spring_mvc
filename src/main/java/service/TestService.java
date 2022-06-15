package service;

import dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    TestDAO testDAO;
    public void testService() {
        testDAO.testDAO();
        System.out.println("Test service");
    }
}

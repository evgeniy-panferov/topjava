package ru.javawebinar.topjava.service.jdbc;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
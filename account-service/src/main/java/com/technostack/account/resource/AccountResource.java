package com.technostack.account.resource;

import ma.baridmedia.imdae.account.client.RegisterServiceClient;
import ma.baridmedia.imdae.account.domain.Account;
import ma.baridmedia.imdae.account.domain.Credentials;
import ma.baridmedia.imdae.account.domain.User;
import ma.baridmedia.imdae.account.dto.*;
import ma.baridmedia.imdae.account.exception.ExceptionHandling;
import ma.baridmedia.imdae.account.repository.AccountRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static ma.baridmedia.imdae.account.constant.SecurityConstant.*;

@RestController
@RequestMapping(path = {"/api/account"})
public class AccountResource extends ExceptionHandling {

    private final RegisterServiceClient registerServiceClient;
    private final AccountRepository accountRepository;

    private static final String SERVICE_ACCOUNT="service_account";

    @Autowired
    public AccountResource(RegisterServiceClient registerServiceClient, AccountRepository accountRepository) {
        this.registerServiceClient = registerServiceClient;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/create")
    //@CircuitBreaker(name = SERVICE_ACCOUNT,fallbackMethod = "feedbackOffService")
    @Transactional(rollbackFor = {RuntimeException.class})
    public Account createAccountOrganization(
            @RequestParam("target") String target,
            @RequestHeader(AUTH_TOKEN)  String token,
            @RequestBody String request) throws  RuntimeException {

        System.out.println(request);
        JSONObject requestJSON = new JSONObject(request);
        return saveAccount(token, requestJSON);
    }

    private Account saveAccount(String token, JSONObject request) {

        System.out.println(request);
        System.out.println(token);

        List<Credentials> credentialsUser = new ArrayList<>();
        credentialsUser.add(Credentials
                .builder()
                    .value((String)request.get("password"))
                .build());

        JSONObject details = (JSONObject) request.get("details");

        User user = User
                .builder()
                    .username((String)request.get("username"))
                    .enabled(true)
                    .totp(false)
                    .firstName(details.has("firstName") ? (String)details.get("lastName") : null)
                    .lastName(details.has("lastName")   ? (String)details.get("lastName") : null)
                    .emailVerified(false)
                    .email(details.get("email").toString())
                    .credentials(credentialsUser)
                .build();

        registerServiceClient.register(token,user);

        //TODO auth user to get token user created
        ResponseAuth responseAuth = registerServiceClient.auth(RequestAuth
                 .builder()
                         .username(request.get("username").toString())
                         .grant_type(GRANT)
                         .password(request.get("password").toString())
                 .build());

        UserInfo userInfo = registerServiceClient.getUserInfo(TOKEN_PREFIX+responseAuth.getAccess_token());

        if(!userInfo.getSub().isEmpty()){

            Account account = Account
                            .builder()
                                .id(userInfo.getSub())
                                .details(((JSONObject) request.get("details")).toMap())
                                .username(request.get("username").toString())
                            .build();
            accountRepository.save(account);
        }

        return accountRepository.findByUsername(request.get("username").toString());
    }

    @Value("${eureka.instance.instance-id}")
    String instanceId;

    @GetMapping("/get")
    public String load() {
        return String.format("Hello from instance %s", instanceId);
    }
}

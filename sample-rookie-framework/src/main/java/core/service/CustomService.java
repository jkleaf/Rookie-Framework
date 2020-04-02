package core.service;

import tk.leaflame.framework.tx.annotation.Service;
import tk.leaflame.framework.tx.annotation.Transaction;

/**
 * @author leaflame
 * @date 2020/4/3 1:18
 */
@Service
public class CustomService {

    @Transaction
    public boolean delete() {
        return true;
    }
}

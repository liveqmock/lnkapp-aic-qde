package org.fbi.aicqde.service;


import org.fbi.linking.processor.Processor;
import org.fbi.linking.processor.ProcessorManagerService;

/**
 * User: zhanrui
 * Date: 13-8-22
 * Time: 上午7:49
 */
public class ProcessorManagerServiceImpl implements ProcessorManagerService {
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public Processor getProcessor(String txnCode) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("org.fbi.aicqde.processor.T" + txnCode + "processor");
        Processor processor = (Processor)clazz.newInstance();

        //TODO
        return processor;
    }
}

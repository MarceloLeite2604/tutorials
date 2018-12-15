package org.marceloleite.tutorials.dsl.delegatesto

import groovy.transform.TypeChecked

def email(
        @DelegatesTo(EmailSpec) // Indicates to whom the closure will be delegated, thus allowing compilation warnings.
                Closure closure) {
    def emailSpec = new EmailSpec()
    def code = closure.rehydrate(emailSpec, this, this)
    code.resolveStrategy = Closure.DELEGATE_ONLY
    code()
}

@TypeChecked
void sendEmail() {
    email {
        from 'dsl-guru@mycompany.com'
        to 'jhon.doe@waitaminute.com'
        subject 'The pope has resigned!'
        body {
            p 'Really, the pope has resigned!'
        }
    }
}

sendEmail()

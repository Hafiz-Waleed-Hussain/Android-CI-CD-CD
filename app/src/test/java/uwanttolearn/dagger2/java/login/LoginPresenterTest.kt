package uwanttolearn.dagger2.java.login

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when
import org.mockito.MockitoAnnotations

/**
 * Created by waleed on 25/03/2018.
 */
@Suppress("IllegalIdentifier")
class LoginPresenterTest {

    private lateinit var presenter: LoginPresenter

    @Mock
    lateinit var view: LoginViewContract

    @Mock
    lateinit var repo: LoginDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(view, repo)
    }

    @Test
    fun `when username empty should show empty username error`() {

        presenter.onLoginClick("", "")
        verify(view, times(1)).showEmptyUsernameError()


        // Optional verifications
        verify(view, never()).showEmptyPasswordError()
        verify(view, never()).showSomethingWentWrong()
        verify(view, never()).startHomeActivity()

    }


    @Test
    fun `when username not empty but password empty should show password error`() {

        presenter.onLoginClick("HafizWaleedHussain", "")
        verify(view, times(1)).showEmptyPasswordError()


        // Optional verifications
        verify(view, never()).showEmptyUsernameError()
        verify(view, never()).showSomethingWentWrong()
        verify(view, never()).startHomeActivity()

    }

    @Test
    fun `when API return false should show something went wrong error`() {

        _when(repo.doLogin()).thenReturn(false)
        presenter.onLoginClick("HafizWaleedHussain", "123456")
        verify(view, times(1)).showSomethingWentWrong()


        // Optional verifications
        verify(view, never()).showEmptyUsernameError()
        verify(view, never()).showEmptyPasswordError()
        verify(view, never()).startHomeActivity()

    }

    @Test
    fun `when all scenarios are successful should verify startHomeActivity`() {
        _when(repo.doLogin()).thenReturn(true)
        presenter.onLoginClick("HafizWaleedHussain", "123456")
        verify(view, times(1)).startHomeActivity()


        // Optional verifications
        verify(view, never()).showEmptyUsernameError()
        verify(view, never()).showEmptyPasswordError()
        verify(view, never()).showSomethingWentWrong()

    }
}
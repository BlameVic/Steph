module Steph
    module Utils
        module ClassMethods
            def command(pattern, options={})
                match(pattern, options)
                match(pattern, options.merge(:use_prefix => false, :react_on => :private))
            end
        end

        def self.included(by)
            by.extend ClassMethods
        end
    end
end